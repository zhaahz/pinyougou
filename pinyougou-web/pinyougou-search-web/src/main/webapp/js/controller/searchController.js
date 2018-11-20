/** 定义搜索控制器 */
app.controller("searchController" ,function ($scope,$sce, baseService) {

    // 定义搜索参数对象
    $scope.searchParam = {};

    /** 定义搜索方法 */
    $scope.search = function () {
        baseService.sendPost("/Search", $scope.searchParam)
            .then(function(response){
                // 获取响应数据 {} Map<String,Object>
                $scope.resultMap = response.data;
            });
    };

    // 将文本转化成html
    $scope.trustHtml = function (html) {
        return $sce.trustAsHtml(html);
    };
   
});
