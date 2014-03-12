/**
 * Project: CheckTree jQuery Plugin Version: 0.2 Project Website:
 * http://static.geewax.org/checktree/ Author: JJ Geewax <jj@geewax.org>
 * 
 * License: The CheckTree jQuery plugin is currently available for use in all
 * personal or commercial projects under both MIT and GPL licenses. This means
 * that you can choose the license that best suits your project, and use it
 * accordingly.
 **************************************
 *leo.china.leo@gmail.com 修改添加支持默认选中的bug，改变打开方式为下滑。
 ***************************************
 */
(function(jQuery) {
	jQuery.fn.checkTree = function(settings) {
		settings = jQuery.extend({
					onExpand : null,
					onCollapse : null,
					onCheck : null,
					onUnCheck : null,
					onHalfCheck : null,
					onLabelHoverOver : null,
					onLabelHoverOut : null,
					labelAction : "expand",
					debug : false
				}, settings);
		var $tree = this;
		$tree.find("li")//all li
				.find("ul").hide()//all li > ul
				.end() //all li 
				.find(":checkbox").change(function() {//all li > checkbox 
					var $all = jQuery(this).siblings("ul").find(":checkbox");//all checkbox :ul checkbox
					var $checked = $all.filter(":checked");
					// All children are checked
					if ($all.length == $checked.length && jQuery(this).attr("checked")) {
						jQuery(this).attr("checked", "checked").siblings(".checkbox").removeClass("half_checked").addClass("checked");
						if (settings.onCheck) settings.onCheck(jQuery(this).parent());
					}
					// All children are unchecked
					else if ($checked.length == 0) {
						jQuery(this).attr("checked", "").siblings(".checkbox").removeClass("checked").removeClass("half_checked");
						if (settings.onUnCheck)
							settings.onUnCheck(jQuery(this).parent());
					}
					// Some children are checked, makes the parent in a half
					// checked state.
					else {
						// Fire parent's onHalfCheck callback only if it's going
						// to change
						if (settings.onHalfCheck && !jQuery(this).siblings(".checkbox").hasClass("half_checked"))
							settings.onHalfCheck(jQuery(this).parent());
						jQuery(this).attr("checked", "checked").siblings(".checkbox").removeClass("checked").addClass("half_checked");
					}
				})
				// .attr("checked", "")
				.hide().end()//all li
				.find("label")// all li > lable
				.click(function() {
							var action = settings.labelAction;
							switch (settings.labelAction) {
								case 'expand' :
									jQuery(this).siblings(".arrow").click();
									break;
								case 'check' :
									jQuery(this).siblings(".checkbox").click();
									break;
							}
						})
				// Add a hover class to the labels when hovering
				.hover(function() { // all li > lable
							jQuery(this).addClass("hover");
							if (settings.onLabelHoverOver)
								settings.onLabelHoverOver(jQuery(this).parent());
						}, function() {
							jQuery(this).removeClass("hover");
							if (settings.onLabelHoverOut)
								settings.onLabelHoverOut(jQuery(this).parent());
						}).end()
				.each(function() { 
					var $arrow = jQuery('<div class="arrow"></div>');
					// If it has children:
					if (jQuery(this).is(":has(ul)")) { //all li
						$arrow.addClass("collapsed"); // Should start collapsed
						// When you click the image, toggle the child list
						$arrow.click(function() {
									jQuery(this).siblings("ul").toggle();
									if (jQuery(this).hasClass("collapsed")) {
										// toggled = settings.expandedarrow;
										jQuery(this).addClass("expanded")
												.removeClass("collapsed");
										if (settings.onExpand)
											settings.onExpand(jQuery(this)
													.parent());
									} else {
										// toggled = settings.collapsedarrow;
										jQuery(this).addClass("collapsed")
												.removeClass("expanded");
										if (settings.onCollapse)
											settings.onCollapse(jQuery(this)
													.parent());
									}
								});
					}
					// Create the image for the checkbox next to the label
					var $checkbox = jQuery('<div class="checkbox"></div>');
					// When you click the checkbox, it should do the
					// checking/unchecking
					$checkbox.click(function() {//checkbox image
								// Make the current class checked
								jQuery(this).removeClass("half_checked").toggleClass("checked");
								// Check/uncheck children depending on our
								// status.
								if (jQuery(this).hasClass("checked")) { //checkbox image
									if (settings.onCheck)
										settings.onCheck(jQuery(this).parent());
									jQuery(this).siblings(":checkbox").attr("checked", "checked");
									jQuery(this) //checkbox image
											.siblings("ul")//checkbox image >ul
											.find(".checkbox")//checkbox image >ul >all checkbox image
											.not(".checked")
											.removeClass("half_checked")
											.addClass("checked")
											.each(function() {
												if (settings.onCheck)
													settings.onCheck(jQuery(this).parent());
											}).siblings(":checkbox")//checkbox image >ul >all checkbox
											.attr("checked", "checked");
								} else {
									// Fire the uncheck callback for this parent
									if (settings.onUnCheck)
										settings.onUnCheck(jQuery(this)
												.parent());
									jQuery(this).siblings(":checkbox").attr("checked", "");
									jQuery(this)
											.siblings("ul")
											.find(".checkbox")
											.filter(".checked")
											.removeClass("half_checked")
											.removeClass("checked")
											.each(function() {
												if (settings.onUnCheck)
													settings.onUnCheck(jQuery(this).parent());
											}).siblings(":checkbox")
											.attr("checked", "");
								}
								// Tell our parent checkbox that we've changed
								jQuery(this).parents("ul").siblings(":checkbox").change(); //checkbox image < ul :checkbox
							});
						jQuery(this).prepend($checkbox).prepend($arrow);//all li
						/*
						 * if ($checkbox.siblings(":checkbox").attr("checked")) {
						 * $checkbox.toggleClass("checked"); }
						 */
				}).end().find(":checkbox").change();
		return $tree;
	};
})(jQuery);