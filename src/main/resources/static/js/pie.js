function getUrlParam(url,name){
    var pattern = new RegExp("[?&]"+name+"\=([^&]+)", "g");
    var matcher = pattern.exec(url);
    var items = null;
    if(matcher != null){
        try{
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        }catch(e){
            try{
                items = decodeURIComponent(matcher[1]);
            }catch(e){
                items = matcher[1];
            }
        }
    }
    return items;
}
$(document).ready(function (){
    var currentUrl = window.location.href;
    var myChart = echarts.init(document.getElementById('main'));
    myChart.showLoading();
    var values=[];
    $.ajax({
        type:"post",
        async:true,
        url:"/visitor/pie",
        data:{
            'id' : getUrlParam(currentUrl,'id')
        },
        dataType:"json",
        success:function (data){
            if (data){
                for (var i=0;i<data.length;i++){
                    var test = {
                        "value":data[i].total,
                        "name":data[i].content
                    };
                    values.push(test);
                }
                myChart.hideLoading();
                myChart.setOption({
                    title:{
                        text:'得票统计',
                        left:'center'
                    },
                    legend:{
                        orient:'vertical',
                        left:'left',
                        data:values
                    },
                    series:[{
                        name:'得票',
                        type:'pie',
                        rediud:'60%',
                        center:['50%','50%'],
                        data:values,
                        itemStyle:{
                            emphasis:{
                                shadowBlur:10,
                                shadowOffsetX:0,
                                shadowColor:'rgba(0,0,0,0.5)'
                            },
                            normal:{
                                label:{
                                    show:true,
                                    formatter:'{b}:{c} ({d}%)'
                                },
                                labelLine:{show:true}
                            }
                        }
                    }]
                });
            }
        },
        error:function (errorMsg){
            alert("图表请求数据失败");
            myChart.hideLoading();
        }
    });
});