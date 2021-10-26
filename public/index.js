let currentLevelOption = null;
let map = []

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
            }
        })
    })
}

function startGame(level){
    if(currentLevelOption !== null){
        currentLevelOption.className = "list-group-item list-group-item-action";
    }
    currentLevelOption = this;
    this.className = "list-group-item list-group-item-action list-group-item-secondary active";
    fetch(`/start?level=${level}`, {
        method: 'GET',
    })
    .then(res => res.json())
    .then(mapObj => loadMap(mapObj))
}