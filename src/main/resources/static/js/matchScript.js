replaceEmptyElementWithComma("round");
replaceEmptyElementWithComma("homeSubstitutes");
replaceEmptyElementWithComma("awaySubstitutes");
replaceEmptyElementWithComma("homeLineup");
replaceEmptyElementWithComma("awayLineup");

let scoreElementById = document.getElementById("score");
let replacedScoreElement = scoreElementById.innerHTML.replaceAll(null, "-");
scoreElementById.innerHTML = replacedScoreElement;


function replaceEmptyElementWithComma(elementId) {

    let elementById = document.getElementById(elementId);
    let elementByIdTxt = elementById.innerText;

    if (elementByIdTxt.includes(null)) {
        elementByIdTxt = "-";
    }

    if (elementByIdTxt === "") {
        elementByIdTxt = "-";
    }
    elementById.innerHTML = elementByIdTxt;
}
