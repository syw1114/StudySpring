const navMenuButton = document.querySelector(".menu-list");
const nav = document.querySelector(".small_menu");

navMenuButton.onmouseover = () =>{
  nav.classList.remove("small_menu");
}

navMenuButton.onmouseout = () => {
  nav.classList.add("small_menu");
}

nav.onmouseover = () =>{
  nav.classList.remove("small_menu");
}

nav.onmouseout = () => {
  nav.classList.add("small_menu");
}