/** 定义首页控制器层 */
app.controller("indexController", function($scope, baseService){

    /**
     * 首页轮播图
     * @param id
     */
    $scope.findContentByCategoryId = function (categoryId) {
        baseService.sendGet("/content/findContentByCategoryId?categoryId="+categoryId)
            .then(function (response) {
                if(response.data){
                    $scope.contentList = response.data;
                }
            });
    }


});