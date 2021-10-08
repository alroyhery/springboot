
function showBtn() {
  var more1 = document.getElementById("moreOne");
  var more2 = document.getElementById("moreTwo");
  var more3 = document.getElementById("moreThree");
  var btnText = document.getElementById("myBtn");

  if (more1.style.display === "none") {
    more2.style.display = "none";
    more3.style.display = "none";
    btnText.innerHTML = "Show Thread";
  } else {
    more1.style.display = "table-cell";
    more2.style.display = "table-cell";
    more3.style.display = "table-row";
    btnText.innerHTML = "Minimize Thread"; 
  }
}