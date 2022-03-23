/**
 * @author  Sebastian
 */

function createColliders(map, patterns) {
    let objects = [];

    for (let pattern of patterns) {
        for (let i = 0; i < map.length; i++) {
            for (let j = 0; j < map[i].length; j++) {
                if (checkPattern(map, pattern, j, i)) {
                    objects[objects.length] = {
                        id : objects.length + 1,
                        x : j * 16,
                        y : /*map[0].length * 16 - */(i * 16) - (pattern.length * 0),
                        width : pattern[0].length * 16,
                        height : pattern.length * 16
                    };
                }
            }
        }
    }

    return objects;
}

function checkPattern(map, pattern, x, y) {
    for (let i = 0; i < pattern.length; i++) {
        for (let j = 0; j < pattern[i].length; j++) {
            if (map[y + i][x + j] !== pattern[i][j]) {
                return false;
            }
        }
    }

    return true;
}

function layerTo2dArr(map) {
    let arr = [];

    let lines = map.split('\n');

    for (let i = 0; i < lines.length; i++) {
        arr[i] = lines[i].split(",");
    }

    return arr;
}

function colgen(layerdata, pattern) {
    let objects = createColliders(layerTo2dArr(layerdata), pattern);

    let rStr = "";

    for (let obj of objects) {
        rStr += "<object id=\"" + obj.id + "\" x=\"" + obj.x + "\" y=\"" + obj.y + "\" width=\"" + obj.width + "\" height=\"" + obj.height + "\"/>";
    }

    return rStr;
}

console.log(colgen(`226,226,226,226,226,226,226,226,226,226,226,177,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354737,177,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354737,226,226,226,226,226,226,226,226,177,2684354740,2684354740,2684354740,2684354737,
226,226,226,226,226,226,226,226,226,226,226,180,182,182,182,182,182,182,182,182,182,3221225652,180,182,182,182,182,182,182,182,2684354743,2684354737,226,226,226,226,226,226,226,180,182,182,182,3221225652,
226,226,226,226,226,226,226,226,226,226,226,180,182,182,182,182,182,182,182,182,182,3221225652,180,182,182,182,182,182,182,182,182,3221225652,201,202,203,204,226,226,226,180,182,182,182,3221225652,
226,226,226,226,226,226,226,226,226,226,226,180,182,182,3221225655,1610612916,1610612916,1610612916,1610612919,182,182,3221225652,180,182,182,182,182,182,182,182,182,3221225652,223,224,225,226,226,226,226,1610612913,1610612919,182,182,3221225652,
177,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,183,182,182,3221225652,226,226,226,180,182,182,3221225652,1610612913,1610612916,1610612916,1610612916,1610612916,1610612916,1610612919,182,182,3221225652,180,182,182,3221225652,226,226,226,226,180,182,182,3221225652,
180,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225652,226,226,226,180,182,182,3221225652,177,2684354740,2684354740,2684354740,2684354740,2684354737,180,182,182,2684354743,183,182,182,3221225652,226,226,226,226,180,182,182,3221225652,
180,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225652,226,226,226,1610612913,1610612916,1610612916,3221225649,180,182,182,182,182,3221225652,180,182,182,182,182,182,182,3221225652,226,226,177,2684354740,183,182,182,3221225652,
180,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225652,226,226,226,226,226,226,226,180,182,182,182,182,3221225652,182,182,182,182,182,182,182,3221225652,226,226,180,182,182,182,182,3221225652,
180,182,182,182,182,182,182,182,182,182,3221225655,1610612916,1610612916,1610612916,3221225649,226,226,226,226,226,226,226,183,182,182,182,182,3221225652,182,182,3221225655,1610612919,182,182,3221225655,3221225649,226,226,180,182,182,182,182,3221225652,
1610612913,1610612916,1610612916,1610612916,1610612919,182,182,182,182,182,2684354743,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,2684354740,183,182,182,182,182,182,3221225652,182,182,2684354743,183,182,182,2684354743,2684354740,2684354740,226,180,182,182,182,182,3221225652,
226,226,226,226,180,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225655,1610612916,1610612916,3221225649,182,182,182,182,182,182,182,182,182,226,180,182,182,182,182,3221225652,
226,226,226,226,180,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225655,3221225649,226,177,182,182,182,182,182,182,182,182,182,182,3221225652,180,182,182,3221225655,1610612916,3221225649,
226,226,226,226,180,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225652,177,2684354740,183,182,182,182,182,182,182,182,182,182,182,3221225652,180,182,182,3221225652,226,226,
2684354740,2684354740,2684354740,2684354740,183,182,182,182,182,182,182,182,3221225655,1610612919,182,182,182,182,3221225655,1610612919,182,182,182,3221225652,180,182,182,182,182,3221225655,1610612916,1610612916,1610612916,182,182,182,182,3221225652,180,182,182,3221225652,226,226,
182,182,182,182,182,182,182,182,182,182,182,182,3221225652,180,182,182,182,182,3221225652,180,182,182,182,3221225652,180,182,182,182,182,2684354743,2684354740,2684354740,2684354740,182,182,182,182,3221225652,180,182,182,3221225652,226,226,
182,182,182,182,182,182,182,182,182,182,182,182,3221225652,180,182,182,182,182,3221225652,180,182,182,182,3221225652,180,182,182,182,182,182,182,182,182,182,182,182,182,2684354743,183,182,182,3221225652,226,226,
182,182,182,182,182,182,182,182,182,182,182,182,3221225652,1610612913,1610612919,182,182,182,3221225652,180,182,182,182,3221225652,180,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225652,226,226,
1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,3221225649,177,183,182,182,182,3221225655,1610612919,182,182,182,3221225652,1610612913,1610612916,1610612916,1610612916,1610612916,1610612916,1610612919,182,182,182,182,182,182,182,182,182,182,3221225652,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,2684354743,183,182,182,182,2684354743,2684354740,2684354740,2684354740,2684354740,2684354740,2684354737,180,182,182,182,3221225655,3221225655,182,182,3221225655,1610612916,1610612916,3221225649,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225652,180,182,182,182,3221225652,180,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,182,182,182,182,182,182,182,182,182,182,182,3221225652,1610612913,1610612919,182,182,3221225652,180,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,1610612913,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612919,182,182,3221225655,1610612916,1610612916,1610612916,3221225649,226,180,182,182,3221225652,180,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,177,2684354740,183,182,182,3221225652,226,226,177,2684354740,2684354740,183,182,182,2684354743,183,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,3221225652,226,226,180,182,182,182,182,182,182,182,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,3221225652,226,226,180,182,182,182,182,182,182,182,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,2684354743,2684354740,2684354740,183,182,182,3221225655,1610612916,1610612916,1610612916,1610612919,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,182,182,182,182,182,182,3221225652,226,226,226,180,182,182,3221225652,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,180,182,182,182,182,182,182,182,182,182,182,3221225652,226,226,226,1610612913,1610612916,1610612916,3221225649,226,226,226,226,226,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1610612913,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,1610612916,3221225649,226,226,226,226,226,226,226,226,226,226,226,226
`, [[["226"]], [["177"]], [["2684354740"]], [["180"]], [["1610612916"]], [["3221225655"]], [["183"]], [["180"]], [["1610612913"]], [["2684354737"]], [["1610612919"]], [["3221225652"]], [["2684354743"]]]));

let tileids = ["225", "179", "182", "201", "202"];