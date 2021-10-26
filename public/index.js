let currentLevelOption = null;

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
    .then(map => {
        console.log(map) // TODO:
    })
}