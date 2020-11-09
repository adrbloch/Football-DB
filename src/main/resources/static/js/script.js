let currentYear = new Date().getFullYear();
let formedYearParagraph = document.getElementById("formedYear");
let formedYearParagraphInnerTxt = formedYearParagraph.innerText;

if (formedYearParagraphInnerTxt != 0) {
    formedYearParagraph.innerHTML = formedYearParagraphInnerTxt + "<br>" + "(" + (currentYear - parseInt(formedYearParagraphInnerTxt)) + " years old)";
}

let altNames = document.getElementById("altNames");
let altNamesInnerTxt = altNames.innerText;
if (altNamesInnerTxt == "") {
    altNamesInnerTxt = "-";
}
altNames.innerHTML = altNamesInnerTxt.split(",").join(",<br>");

