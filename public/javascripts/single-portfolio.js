//<![CDATA[
jQuery(document).ready(function() {
		
		var portfolio_gallery = jQuery('.portfolio-sidebar .gallery');
		jQuery('.single-portfolio article').append(portfolio_gallery);
		
		var portfolio_image = jQuery('.portfolio-sidebar .single-portfolio-image');
		jQuery('.single-portfolio article').append(portfolio_image);
		
		var portfolio_images = jQuery('.portfolio-sidebar img');
		jQuery('.single-portfolio article').append(portfolio_images);
		
		var portfolio_video = jQuery('.portfolio-sidebar embed, .portfolio-sidebar iframe, .portfolio-sidebar object');
		jQuery('.single-portfolio article').append(portfolio_video);
		
		jQuery('.single-portfolio').show();
});
//]]>