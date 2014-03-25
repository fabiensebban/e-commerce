jQuery(document).ready(function() {  
	
	//	Initialize Homepage Slider
	jQuery(".content-page .flexslider").flexslider({
		animationSpeed: 200,
		animation: "slide",
		controlNav: false,
		directionNav: false,
		slideshow: false,
		touch: true
	});
	
	  // Portfolio/Blog Flexslider Carousel
	  jQuery('.home-products .flexslider').flexslider({
	    animation: "slide",
	    animationLoop: false,
		animationSpeed: 400,
	    itemWidth: 220,
		itemMargin: 40,
		controlNav: false,
		directionNav: true,
		slideshow: false,
		prevText: "",
		nextText: "",
		move: 1,
		touch: true
	  });

});  