let currentLevelOption = null;
let map = []
let usageModal = new bootstrap.Modal(document.getElementById('usage-modal'), {
    keyboard: false
})
let winModal = new bootstrap.Modal(document.getElementById('win-modal'), {
    keyboard: false
})


function fetchLevels(){
    fetch("/levels", {
        method: 'GET',
    })
    .then(res => res.json())
    .then(levels => {
        // Get level list
        levelList = document.getElementById("level-list");
        // Remove all children
        while(levelList.firstChild){
            levelList.removeChild(levelList.firstChild);
        }
        // Create option
        levels.forEach(level => {
            option = document.createElement("a");
            option.href = "#";
            option.className = "list-group-item list-group-item-action";
            option.innerText = level;
            option.addEventListener("click", startGame.bind(option, level))
            levelList.appendChild(option);
        });
    })
}

function loadMap(mapObj){
    wrapper = document.getElementById("map-wrapper");
    // Clean previous
    while(wrapper.firstChild){
        wrapper.removeChild(wrapper.firstChild);
    }
    // Setup map
    mapWidth = mapObj[0].length
    mapHeight = mapObj.length
    unitSize = Math.min(Math.floor((wrapper.clientWidth - 40) / mapWidth), Math.floor((wrapper.clientHeight - 40) / mapObj.length));
    originX = ((wrapper.clientWidth) - unitSize * mapWidth) / 2;
    originY = ((wrapper.clientHeight) - unitSize * mapHeight) / 2;
    map = []
    mapObj.forEach((row, y) => {
        map.push([]);
        row.forEach((cell, x) => {
            elem = wrapper.appendChild(document.createElement("img"));
            elem.width = unitSize;
            elem.height = unitSize;
            elem.style.top = `${originY + (y * unitSize)}px`;
            elem.style.left = `${originX + (x * unitSize)}px`;
            elem.style.position = "absolute";
            switch(cell){
                case "#":
                    elem.src = "wall.svg";
                break;
                case "@":
                    elem.src = "head.svg";
                break;
                case "$":
                    elem.src = "crate.svg";
                break;
                case "%":
                    elem.src = "locked_crate.svg";
                break;
                case ".":
                    elem.src = "target.svg";
                    break;
                case null:
                    elem.src = "null.svg";
                break;
            }
            map[y].push(elem);
        })
    })
}

function renderMap(mapObj){
    mapObj.forEach((row, y) => {
        row.forEach((cell, x) => {
            switch(cell){
                case "#":
                    map[y][x].src = "wall.svg";
                break;
                case "@":
                    map[y][x].src = "head.svg";
                break;
                case "$":
                    map[y][x].src = "crate.svg";
                break;
                case "%":
                    map[y][x].src = "locked_crate.svg";
                break;
                case ".":
                    map[y][x].src = "target.svg";
                break;
                case null:
                    map[y][x].src = "null.svg";
                break;
            }
        })
    })
}

function startGame(level){
    // Set current option
    if(currentLevelOption !== null){
        currentLevelOption.className = "list-group-item list-group-item-action";
    }
    currentLevelOption = this;
    this.className = "list-group-item list-group-item-action list-group-item-secondary active";
    // Start game
    fetch(`/start?level=${level}`, {
        method: 'GET',
    })
    .then(res => res.json())
    .then(mapObj => loadMap(mapObj))
    .then(() => {
        usageModal.show();
    })
}

function resetGame(){
    if(currentLevelOption !== null){
        fetch(`/restart`, {
            method: 'GET',
        })
        .then(res => res.json())
        .then(mapObj => renderMap(mapObj))
    }
}

function userMove(event){
    if(currentLevelOption !== null){
        // Get direction
        direction = null;
        switch(event.code){
            case 'KeyW':
            case 'ArrowUp':
                direction = 2;
                break;
            case 'KeyA':
            case 'ArrowLeft':
                direction = 0;
                break;
            case 'KeyS':
            case 'ArrowDown':
                direction = 3;
                break;
            case 'KeyD':
            case 'ArrowRight':
                direction = 1;
                break;
            default:
                return;
        }
        event.stopPropagation()
        // Move
        fetch(`/move?direction=${direction}`, {
            method: 'GET',
        })
        .then(res => res.json())
        .then(mapObj => {
            renderMap(mapObj["map"])
            if(mapObj["win"]){
                winModal.show()
            }
        })
    }
}
