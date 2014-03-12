/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/



CKEDITOR.editorConfig = function( config )
{
	config.scayt_autoStartup = false;
	config.skin='v2';
    config.toolbar = 'JStoolbar';
    config.startupFocus = false;
    config.filebrowserUploadUrl = app_path+'/eop/ckuploader.do';

	config.toolbar_JStoolbar =
	[
		
		['Source','Preview','Templates'],
		['Cut','Copy','Paste','PasteText','PasteFromWord'],
		['Undo','Redo'],
		['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
		['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
		['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
		['Link','Unlink','Anchor'],
		['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar'],
		['Styles','Format','Font','FontSize'],
		['TextColor','BGColor'],
		['Maximize']

	];

};
