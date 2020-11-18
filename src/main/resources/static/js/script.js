let currentYear = new Date().getFullYear();
let formedYearParagraph = document.getElementById("formedYear");
if (formedYearParagraph != null){
    let formedYearParagraphInnerTxt = formedYearParagraph.innerText;

    if (formedYearParagraphInnerTxt != 0) {
        formedYearParagraph.innerHTML = formedYearParagraphInnerTxt + "<br>" + "(" + (currentYear - parseInt(formedYearParagraphInnerTxt)) + " years old)";
    }
}

let elementById = document.getElementById("belt");
    let elementsByTagName = elementById.getElementsByTagName("p");

    for (let element of elementsByTagName){
        if (element.innerText == ""){

            element.innerHTML = "-";
        }
        element.innerHTML = element.innerHTML.split(",").join(",<br>");
    }





