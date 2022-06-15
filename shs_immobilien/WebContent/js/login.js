"use strict";

document.addEventListener("DOMContentLoaded", () => {

	// check if cookie is set and display a value
	document.getElementById("mail").value = getCookie("sps-username");

	document.getElementById("login-form").addEventListener("submit", () => {
				
		if (document.getElementById("remember").checked) {
			setCookie("sps-username", document.getElementById("mail").value);
		} else {
			setCookie("sps-username", "");
		}
		
	});
	
});


// cookie functions taken from w3schools
// https://www.w3schools.com/js/js_cookies.asp

function setCookie(cname, cvalue, exdays = 7) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  let expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
  let name = cname + "=";
  let ca = document.cookie.split(';');
  for(let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}