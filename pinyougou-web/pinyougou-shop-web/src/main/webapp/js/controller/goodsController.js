/** 定义控制器层 */
app.controller('goodsController', function($scope, $controller, baseService,$http){

    /** 指定继承baseController */
    $controller('baseController',{$scope:$scope});


    /** 分页查询(带查询条件或不带查询条件) */
    // this.findByPage = function(url, page, rows, data){
    //     /** 定义分页URL */
    //     url += '?page='+ page +'&rows=' + rows;
    //     if (data && JSON.stringify(data) != "{}"){
    //         return $http({
    //             method : 'get',
    //             url : url,
    //             params : data
    //         });
    //     }else{
    //         return this.sendGet(url);
    //     }
    // };

    /** 定义搜索对象 */
    $scope.searchEntity = {};

    $scope.search = function (page,rows) {
      baseService.findByPage("/goods/findByPage",page,rows,$scope.searchEntity)
          .then(function (response) {

                  $scope.paginationConf.totalItems = response.data.total;
                  $scope.dataList = response.data.rows;

          });
    };

    //定义状态数组
    $scope.status = ['未审核','已审核','审核未通过','关闭'];

    /** 添加或修改 */
    $scope.saveOrUpdate = function(){
        /**獲取富文本編輯器的內容*/
        $scope.goods.goodsDesc.introduction = editor.html();

        /** 发送post请求 */
        baseService.sendPost("/goods/save", $scope.goods)
            .then(function(response){
                if (response.data){
                    //清空表单
                    $scope.goods = {};
                    //清空富文本編輯器中的內容
                    editor.html('');
                }else{
                    alert("添加商品失败！");
                }
            });
    };




    /**查詢一級商品分類*/
    $scope.findItemCatByParentId = function (parentId,name) {
        baseService.sendGet("/itemCat/findItemCatByParentId",
            "parentId="+parentId).then(function (response) {
                $scope[name] = response.data.itemCatList;
        });

    };


    //有关于angularJS中的数组
    //如 $scope[name] push方法 put等等

    /**监控goods.category1Id变量,查询二级分类*/
    $scope.$watch("goods.category1Id",function (newValue,oldValue) {
       if(newValue){
           /** 根据选择的值查询二级分类 */
           $scope.findItemCatByParentId(newValue, "itemCatList2");
       }else {
           $scope.itemCatList2 = [];
       }
    });

    /** 监控 goods.category2Id 变量，查询三级分类 */
    $scope.$watch('goods.category2Id', function(newValue, oldValue){
        if (newValue){
            /** 根据选择的值查询三级分类 */
            $scope.findItemCatByParentId(newValue, "itemCatList3");
        }else{
            $scope.itemCatList3 = [];
        }
    });




    // 定义数据存储格式
    $scope.goods = {goodsDesc : { itemImages : []}};


    // 图片异步上传方法
    $scope.uploadFile = function () {
        // 调用服务层方法
        baseService.uploadFile().then(function(response){
            // 获取响应数据
            // 需要显示上传的的图片 {url:'', status : 200}
            if (response.data.status == 200){
                // 获以图片URL
                // {"color":"","url":""}
                $scope.picEntity.url = response.data.url;
            }else{
                alert("上传失败！");
            }
        });
    };

    // 保存图片到商品描述对象中
    $scope.addPic = function () {
        $scope.goods.goodsDesc.itemImages.push($scope.picEntity);
    };

    // 从商品描述对象中删除图片
    $scope.removePic = function (idx) {
        $scope.goods.goodsDesc.itemImages.splice(idx, 1);
    };


    // 监听"goods.category3Id"三级分类变量发生改变，查找类型模板id
    $scope.$watch("goods.category3Id", function (newVal, oldVal) {
        if (newVal){ // 判断newVal不是undefined、null
            // 迭代三级商品分类数组 List<ItemCat> [{},{}]
            for (var i = 0; i < $scope.itemCatList3.length; i++){
                // 取一个数组元素
                var obj = $scope.itemCatList3[i];
                if (obj.id == newVal){
                    // 获取类型模板id
                    $scope.goods.typeTemplateId = obj.typeId;
                }
            }
        }else{
            $scope.goods.typeTemplateId = null;
        }
    });

    // 监听"goods.typeTemplateId"类型模板变量发生改变，根据主键id查询类型模板对象
    $scope.$watch("goods.typeTemplateId", function (newVal, oldVal) {
        if (newVal){ // 判断newVal不是undefined、null
            baseService.sendGet("/typeTemplate/findOne?id=" + newVal)
                .then(function(response){
                    // 获取响应数据 (品牌)
                    $scope.brandIds = JSON.parse(response.data.brandIds);
                    // 获取扩展属性
                    $scope.goods.goodsDesc.customAttributeItems = JSON
                        .parse(response.data.customAttributeItems);
                });


            //发送异步请求,查询规格选项数据
            baseService.sendGet("/typeTemplate/findSpecByTemplateId?id="+newVal).then(
                function (response) {

                    $scope.specList = response.data;
                }
            );
        }else{
            $scope.brandIds = null;
        }
    });


    /**将选中的goods存储到数据库中*/
    /**以json字符串的格式*/




    /**
     定 义 SKU
     数 组 变 量 ， 并 初 始 化
     */
    $scope.goods.items = [{spec:{}, price:0, num:9999, status:'0', isDefault:'0'}];


    //循环用户选择的规格，根据规格名称和已选择的规格选项对原数组进行扩充，添加规 格名称和值，
    // 新增的记录数与选择的规格选项个数相同，生成的顺序如下图


    /**生成SKU表 为item表*/
    //需要的属性spec, price, num, status, isDefault,还有att...


    //1.定义SKU列表,并初始化

    //2.获取用户选中的规格选项列表

    //3.迭代获取用户选中的规格列表
    //3.1 取一个数组元素
    //[{"attributeValue":["移动4G","联通4G","电信4G"],"attributeName":"网络"}]

    //3.2 扩充SKU,将数组元素中加到SKU
    //怎么加? 将attributeValue和attributeName加入



    //5. 定义新的SKU数组 newItems

   //4. 定义扩充方法
    //4.1 迭代[attributeValue  "attributeValue":["移动4G","联通4G","电信4G"]
    //6. 将迭代的 [{"attributeValue":["移动4G","联通4G","电信4G"],"attributeName":"网络"}]加到spec中



























    /** 查询条件对象 */
    $scope.searchEntity = {};
    /** 分页查询(查询条件) */
    $scope.search = function(page, rows){
        baseService.findByPage("/goods/findByPage", page,
			rows, $scope.searchEntity)
            .then(function(response){
                /** 获取分页查询结果 */
                $scope.dataList = response.data.rows;
                /** 更新分页总记录数 */
                $scope.paginationConf.totalItems = response.data.total;
            });
    };



    /** 显示修改 */
    $scope.show = function(entity){
       /** 把json对象转化成一个新的json对象 */
       $scope.entity = JSON.parse(JSON.stringify(entity));
    };

    /** 批量删除 */
    // $scope.delete = function(){
    //     if ($scope.ids.length > 0){
    //         baseService.deleteById("/goods/delete", $scope.ids)
    //             .then(function(response){
    //                 if (response.data){
    //                     /** 重新加载数据 */
    //                     $scope.reload();
    //                 }else{
    //                     alert("删除失败！");
    //                 }
    //             });
    //     }else{
    //         alert("请选择要删除的记录！");
    //     }
    // };

    //逻辑删除商品(让isDelete为1)

    $scope.delete = function (isDelete) {

        if($scope.ids.length > 0){
            baseService.sendGet("/goods/updateIsDelete?isDelete="+isDelete+"&ids="+$scope.ids)
                .then(function (response) {
                    if(response.data){
                        /** 重新加载数据 */
                        $scope.reload();
                    }else {
                        alert("删除失败!");
                    }
                });
        }else {
            alert("请选择您要删除的商品!")
        }
    };

    //商品的上架与下架
    $scope.updateIsMarketable = function (isMarketable) {
        if($scope.ids.length>0){
            baseService.sendPost("/goods/updateIsMarketable?isMarketable="+isMarketable,$scope.ids)
                .then(function (response) {
                    if(response.data){
                        $scope.reload();
                    }else {
                        alert("操作失败");
                    }
                });
        }else {
            alert("请选择您要操作的商品!");
        }
    }



});