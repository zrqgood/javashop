function FileProgress(file, targetID) {
	this.fileProgressID = file.id;
	this.fileProgressWrapper = document.getElementById(this.fileProgressID);
	if (!this.fileProgressWrapper) {
		
		var progressContainer = document.createElement("div");
		progressContainer.className = "progressContainer";
		progressContainer.id="container_" + this.fileProgressID;
		
		this.fileProgressWrapper = document.createElement("div");
		this.fileProgressWrapper.className = "proWrapper";
		this.fileProgressWrapper.id = this.fileProgressID;
		
		var fileProgressState = document.createElement("div");
		var fileProgressText = document.createElement("div");
		
		fileProgressState.className = "progressState";
		fileProgressState.style.width="0%";
		fileProgressText.className = "progressText";
		
		this.fileProgressWrapper.appendChild(fileProgressState);
		this.fileProgressWrapper.appendChild(fileProgressText);
		
		var deleteBar = document.createElement("div");
		var delLink = document.createElement("A");
		delLink.href="javascript:;";
		delLink.setAttribute("wrapper", progressContainer.id );
		delLink.className = "deleteBtn new";
		delLink.innerHTML ="&nbsp;";
		 
		deleteBar.appendChild(delLink);
		
		progressContainer.appendChild(this.fileProgressWrapper);
		progressContainer.appendChild(deleteBar);
		
		document.getElementById(targetID).appendChild(progressContainer);
		
	} else {
		//this.fileProgressElement = this.fileProgressWrapper.firstChild;
	}
	
	
}

FileProgress.prototype.setProgress = function (percentage) {
//	this.fileProgressElement.innerHTML = percentage;
	
	this.fileProgressWrapper.childNodes[0].style.width = percentage + "%";
	this.fileProgressWrapper.childNodes[1].innerHTML=  percentage + "%";
};


FileProgress.prototype.setStatus = function (status) {	
	this.fileProgressWrapper.childNodes[1].innerHTML = status;
};

FileProgress.prototype.setSuccess = function (fileName,fileid) {
	 
	var link =  document.createElement("A");
	link.href="javascript:;"
	link.setAttribute("img",fileName);
	link.setAttribute("wrapper",fileid);
	var img  = document.createElement("img");
	img.src= fileName;
	img.border=0;
	img.className = "uploadImg";
	link.appendChild(img);
	
	var input = document.createElement("input");
	input.type="hidden";
	input.name="picnames";
	input.id="input_"+ fileid;
	input.value=fileName;
	
	
	this.fileProgressWrapper.removeChild(this.fileProgressWrapper.childNodes[0]);
	this.fileProgressWrapper.removeChild(this.fileProgressWrapper.childNodes[0]);
	this.fileProgressWrapper.appendChild(link);
	this.fileProgressWrapper.appendChild(input);
	 
};




