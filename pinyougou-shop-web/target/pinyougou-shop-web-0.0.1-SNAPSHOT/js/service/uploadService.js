app.service("uploadService",function($http){
	
	this.uploadFile=function(){
		
		var formdata=new FormData();
		formdata.append('file',file.files[0]);
		
		return $http({
			url:'../upload.do',
			method:'POST',
			data:formdata,
			headers:{'Content-Type':undefined},
			transformRequest:angular.identity
			
		
		});
		
	}
	
});