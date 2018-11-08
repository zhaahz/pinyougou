app.controller("brandController",function ($scope,$controller,baseService) {

    /**指定继承baseController*/
    $controller("baseController",{$scope:$scope});

    //定义函数方法
    $scope.findAll=function () {
        baseService.get("/brand/findAll").then(function (response) {
            $scope.dataList = response.data;
        });

        // 笔记 10/30 then后面的函数是什么意思呢,response和response.data
    };


    /** 定义搜索对象 */
    $scope.searchEntity = {};


    /** 分页查询品牌信息 */
    $scope.search = function(page, rows){
        baseService.findByPage("/brand/findByPage",page,
            rows,$scope.searchEntity)
            .then(function(response){
                $scope.dataList = response.data.rows;
                $scope.paginationConf.totalItems = response.data.total;
            });
    };




    $scope.saveOrUpdate=function () {
        var url = "/save";
        if($scope.entity.id){
            url = "/update";
        }
        baseService.post("/brand"+url,$scope.entity)
            .then(function (response) {

                if(response.data){
                    //$scope.findAll();
                    $scope.reload();
                }else {
                    alert("添加失败,请重新添加!");
                }
            });
    };

    /** 添加与修改品牌 */
    /** 显示修改，为修改表单绑定当行数据 */
    $scope.show = function(entity){
        // 把entity的json对象转化成一个新的json对象
        $scope.entity = JSON.parse(JSON.stringify(entity));
    };







    /** 批量删除品牌 */
    //删除操作
    $scope.delete = function(){
        if ($scope.ids.length > 0){
            /** 调用服务层 */
            baseService.deleteById("/brand/delete", $scope.ids).then(
                function(response){
                    if(response.data){
                        /** 重新加载品牌数据 */
                        $scope.reload();
                    }
                }
            );
        }else{
            alert("请选择要删除的品牌！");
        }
    };
});