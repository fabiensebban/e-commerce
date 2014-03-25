//<![CDATA[
jQuery(window).load(function() {

	jQuery('.product-image-zoom').each(function(){
		
		var zoom = jQuery(this).attr('zoom');
		jQuery(this).zoom({url: zoom });
	});
	
	jQuery('#read-reviews').click(function(){
		jQuery(".reviews_tab a").triggerHandler('click');
	})

});
//]]>