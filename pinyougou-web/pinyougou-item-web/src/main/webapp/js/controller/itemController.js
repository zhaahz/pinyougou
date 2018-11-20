// 商品详情控制器
app.controller('itemController', function ($scope) {

    // 购买数量加减操作
    $scope.addNum = function (x) {
        $scope.num += x;
        // 判断购买数量不能少于1
        if ($scope.num < 1){
            $scope.num = 1;
        }
    };
    // 监控num变量
    $scope.$watch('num', function (newVal, oldVal) {
        if (newVal){
            if (newVal < 1 || !/^\d$/.test(newVal)){
                $scope.num = 1;
            }
        }
    });

    // 定义用户选中的规格
    $scope.specItems = {};

    // 用户选择规格
    $scope.selectSpec = function (specName, optionName) {
        $scope.specItems[specName] = optionName;


        // 搜索指定的SKU(查找对应的SKU商品)
        searchSku();
    };

    // 判断规格选项是否选中
    $scope.isSelected = function (specName, optionName) {
        return $scope.specItems[specName] == optionName;
    };

    // 加载默认的SKU
    $scope.loadSku = function () {
        // 取itemList中的第一个元素(默认的SKU商品)
        $scope.sku = itemList[0];
        // {"网络":"移动4G","机身内存":"64G"}
        $scope.specItems = JSON.parse($scope.sku.spec);
    };

    /** 根据用户选中的规格，查询指定的SKU */
    var searchSku = function () {
        for (var i = 0; i < itemList.length; i++){
            // 取一个数组元素(tb_item表中的一行数据)
            var item = itemList[i];
            // item.spec : json字符串
            if (item.spec == JSON.stringify($scope.specItems)){
                $scope.sku = item;
                return;
            }
        }
    };

    // 为加入购物车按钮绑定点击事件
    $scope.addToCart = function () {
        alert('SKU的id: ' + $scope.sku.id + "，购买数量：" + $scope.num);
    };


});