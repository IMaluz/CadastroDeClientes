$(function(){
	var nav_offset_top = $('header').height() + 50; 
	function navbarFixed(){
		if ( $('.header_one').length ){ 
			$(window).scroll(function() {
				var scroll = $(window).scrollTop();   
				if (scroll >= nav_offset_top ) {
					$(".header_one").addClass("navbar_fixed");
				} else {
					$(".header_one").removeClass("navbar_fixed");
				}
			});
		};
	};
	navbarFixed();

	var page = $('html, body'); 
	$('a.nav-link').click(function(){
		page.animate({
			scrollTop: $( $.attr(this, 'href') ).offset().top
		}, 900);
		return false;
	});
	var page = $('html, body'); 
	$('a.navbar-brand').click(function(){
		page.animate({
			scrollTop: $( $.attr(this, 'href') ).offset().top
		}, 900);
		return false;
	});
});

const debounce = function(func, wait, immediate) {
  let timeout;
  return function(...args) {
    const context = this;
    const later = function () {
      timeout = null;
      if (!immediate) func.apply(context, args);
    };
    const callNow = immediate && !timeout;
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
    if (callNow) func.apply(context, args);
  };
};

const target = document.querySelectorAll('[data-anime]');
const animationClass = 'animate';

function animeScroll() {
  const windowTop = window.pageYOffset;
  target.forEach(function(element) {
    if((windowTop) > element.offsetTop) {
      element.classList.add(animationClass);
    } else {
      element.classList.remove(animationClass);
    }
  })
}

animeScroll();

if(target.length) {
  window.addEventListener('scroll', debounce(function() {
    animeScroll();
  }, 200));
}