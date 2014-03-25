//<![CDATA[
jQuery(window).load(function() {
	// Filtered Portfolio
	jQuery('.galleries.one-column .portfolio-filtered').isotope({
		// options
		itemSelector: '.portfolio',
		layoutMode: 'straightDown'
	});

	jQuery('.galleries.four-column .portfolio-filtered, .galleries.three-column .portfolio-filtered, .galleries.two-column .portfolio-filtered').isotope({
		// options
		itemSelector: '.portfolio',
		layoutMode: 'fitRows'
	});
});
jQuery(document).ready(function() {


	//Display Cart icon
	jQuery(window).scroll(function(){
	        if (jQuery(this).scrollTop() > 50) {
	            jQuery('.hidecart-icon').fadeIn('fast');
	        } else {
	            jQuery('.hidecart-icon').fadeOut('fast');
	        }
	    });

		// Product image hover
		jQuery('.product-images').hover(function(){

				jQuery(this).find(".product-overlay").stop().animate({
				    opacity: 1,
				}, 200 );
			    }, function(){

				jQuery(this).find(".product-overlay").stop()	.animate({
					    opacity: 0,
					}, 200 );
			});

	// Change slide on hover
	jQuery('.custom-controls li').hover(function(){

		jQuery(this).parents('.flexslider').flexslider(jQuery(this).index());

		jQuery(this).siblings().removeClass('active');
		jQuery(this).addClass('active');
	    }, function(){
	});


	/*  Add .last class to Footer widgets  */
	var $footer_widgets_number = jQuery("#footer-widgets div.footer-widget").length;
	var $footer_widgets = jQuery("#footer-widgets div.footer-widget");

	if (!($footer_widgets.length == 0)) {
		jQuery("#footer-widgets .footer-widget").addClass('column-'+$footer_widgets_number);
		$footer_widgets.each(function (index, domEle) {

			// domEle == this
			// Set maximum number of widgets in a row
			if( (index+1) >= 5) {
				if ((index+1)%5 == 0) jQuery(domEle).addClass("last").after("<div class='clear'></div>");
			} else {
				if ((index+1)%$footer_widgets_number == 0) jQuery(domEle).addClass("last").after("<div class='clear'></div>");
			}
		});
	}

	var $author_grid = jQuery(".team-page-grid .author-wrap");

    if (!($author_grid.length == 0)) {
		$author_grid.each(function (index, domEle) {
		// domEle == this
		if ((index+1)%4 == 0) jQuery(domEle).addClass("last").after("<div class='clear'></div>");
		});
	}

	var $home_potfolio = jQuery("#home-portfolio .galleries article");

    if (!($home_potfolio.length == 0)) {
		$home_potfolio.each(function (index, domEle) {
		// domEle == this
		if ((index+1)%3 == 0) jQuery(domEle).addClass("last").after("<div class='clear'></div>");
		});
	}
	
	/*  Add .last class to Home widgets  */
       var $home_widget = jQuery("#home-widgets div.home-widget");

       if (!($home_widget.length == 0)) {
		$home_widget.each(function (index, domEle) {
			// domEle == this
			if ((index+1)%3 == 0) jQuery(domEle).addClass("last").after("<div class='clear'></div>");
		});
	}


	/*  Add .last class to Home widgets  */
       var $home_product = jQuery(".home-page-sidebar .home-products ul.products li.product, .shop-sidebar.archive ul.products li.product");

       if (!($home_product.length == 0)) {
		$home_product.each(function (index, domEle) {
			// domEle == this
			if ((index+1)%3 == 0) jQuery(domEle).addClass("last").after("<div class='clear'></div>");
		});
	}
	
	/*  Add .last class to Home widgets  */
       var $home_product_no_sidebar = jQuery(".shop-no-sidebar.archive ul.products li.product");

       if (!($home_product_no_sidebar.length == 0)) {
		$home_product_no_sidebar.each(function (index, domEle) {
			// domEle == this
			if ((index+1)%4 == 0) jQuery(domEle).addClass("last").after("<div class='clear'></div>");
		});
	}


	jQuery('li.product .product-thumb-wrap').hover( function(){
	jQuery(this).find('.button').show() } , function(){jQuery(this).find('.button').hide() });

	/*  SuperFish  */
	jQuery('ul.nav, ul.top').superfish({
		delay:       100,                            // one second delay on mouseout
		animation:   {opacity:'show'},  // fade-in and slide-down animation
		speed:       100,                          // faster animation speed
		autoArrows:  true,                           // disable generation of arrow mark-up
		dropShadows: false                          // disable drop shadows
	});

	/*  Initialize Shadowbox  */

	Shadowbox.init({
		overlayColor: "#fff",
	    overlayOpacity: 0.9,
	    autoplayMovies: false,
	    viewportPadding: 20
	});
	
	// Portfolio image hover effect
	var $SingleGalItem = jQuery('.gallery-image-wrap');
	$SingleGalItem.hover(function(){

		jQuery(this).find('.portfolio-overlay').stop(true, true).fadeIn("fast");
	}, function(){
		jQuery(this).find('.portfolio-overlay').stop(true, true).fadeOut("fast");

	});

	// Collapsable sidbar widgets
	jQuery(".sidebar-header").click(function(){
		jQuery(this).next('.widget-content').toggle();
		jQuery(this).toggleClass('widget-toggled');

	});


	// Like Icons
    if(jQuery('.like-count').length) {
    	jQuery('.like-count').live('click',function() {
    		var id = jQuery(this).attr('id');
    		id = id.split('like-');
    		jQuery.ajax({
    			url: ufo.ajaxurl,
    			type: "POST",
    			dataType: 'json',
    			data: { action : 'ufo_liked_ajax', id : id[1] },
    			success:function(data) {
    				if(true==data.success) {
    					jQuery('#like-'+data.postID).text(" " + data.count);
    					jQuery('#like-'+data.postID).addClass('active');
    				}
    			}
    		});
    		return false;
    	});
    }

	// Like Active Class
	jQuery('.like-count').each(function() {
	    var $like_count = 0;
		var $like_count = jQuery(this).text();
		if($like_count != 0) {
	        jQuery(this).addClass('active');
	    }
	});

	// Social Media Icons / Sharre
	jQuery('#twitter').sharrre({
	        share: {
	            twitter: true
	        },
	        template: '<a class="share" href="#"><div class="icon-twitter"></div></a>',
	        enableHover: false,
	        click: function(api, options){
	            api.simulateClick();
	            api.openPopup('twitter');
	        }
	    });

	    jQuery('#facebook').sharrre({
	        share: {
	            facebook: true
	        },
	        template: '<a class="share" href="#"><div class="icon-facebook"></div></a>',
	        enableHover: false,
	        click: function(api, options){
	            api.simulateClick();
	            api.openPopup('facebook');
	        }
		});

		jQuery('#pinterest').sharrre({
	        share: {
	            pinterest: true
	        },
	        template: '<a class="share" href="#"><div class="icon-pinterest"></div></a>',
	        enableHover: false,
			urlCurl: '',
	        click: function(api, options){
	            api.simulateClick();
	            api.openPopup('pinterest');
	        }
		});

		jQuery('#googleplus').sharrre({
	        share: {
	            googlePlus: true
	        },
	        template: '<a class="share" href="#"><div class="icon-google-plus"></div></a>',
	        enableHover: false,
			urlCurl: '',
	        click: function(api, options){
	            api.simulateClick();
	            api.openPopup('googlePlus');
	        }
		});

		//Sortable portfolio menu
		jQuery('.portfolio-tabs a').click(function(e){
			e.preventDefault();

			var selector = jQuery(this).attr('data-filter');
			jQuery('.portfolio-filtered').isotope({ filter: selector });

			jQuery(this).parents('ul').find('li').removeClass('active');
			jQuery(this).parent().addClass('active');
		});
		
		// Header Search Bar
		jQuery('#search-toggle').click(function(){
			jQuery('#header-wrap-outer #menu-main').fadeToggle();
			jQuery('#header-wrap-outer #searchbar').fadeToggle();
			jQuery('#header-wrap-outer #searchbar .search-form .s ').focus();
			
		})
});
//]]>