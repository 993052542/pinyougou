app.controller('baseController',function($scope){
	
			//分页控件配置
			$scope.paginationConf = {
					 currentPage: 1,
					 totalItems: 10,
					 itemsPerPage: 10,
					 perPageOptions: [10, 20, 30, 40, 50],
					 onChange: function(){
					        $scope.reloadList();//重新加载
					 }
			}; 
			
			$scope.reloadList=function(){
				$scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage)
			}
			
			$scope.selectIds = []
			//用户勾选复选框
			$scope.updateSelection=function($event,id){
				if($event.target.checked){
					$scope.selectIds.push(id)//通过push向集合中添加元素
				}else{
					var index = $scope.selectIds.indexOf(id)//通过id查找值的位置
					$scope.selectIds.splice(index,1)//参数1.移除的位置  参数2.移除的 个数
				}
			}
			
			$scope.jsonToString=function(jsonString,key){
				var json=JSON.parse(jsonString);
				var value="";

				for(var i=0;i<json.length;i++){
					if(i>0){
						value+=",";
					}
					value+=json[i][key];
				}
				
				return value;
			}
			//从集合中按照key查询对象
			$scope.searchObjectByKey=function(list,key,keyValue){
				
				for(var i=0; i<list.length; i++){
					if(list[i][key]==keyValue){
						return list[i];
					}
				}
				return null;
				
			}
	
});