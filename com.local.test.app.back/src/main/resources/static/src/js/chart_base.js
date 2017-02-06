//渲染折线图
function renderLineChart(option, dom) {
    // 指定图标的默认配置项
    var objX = null;
    var objY = null;
    var defaultX = {
        type: 'category',
        boundaryGap: false,
        axisLine: {
            show: false
        },
        axisTick: {
            show: false,
            alignWithLabel: true
        },
        axisLabel: {
            margin: 20,
            textStyle: {
                color: '#798da8'
            }
        }
    }

    var defaultY = {
        type: 'value',
        splitNumber: 4,
        axisLine: {
            show: false
        },
        axisTick: {
            show: false,
            alignWithLabel: true
        },
        axisLabel: {
            margin: 21,
            textStyle: {
                color: '#798da8'
            },
            formatter: '{value}%'
        },
        splitLine: {
            lineStyle: {
                color: '#37526b'
            }
        }
    }

    if (option.xAxis)
        objX = $.extend(true, {}, defaultX, option.xAxis[0]);

    if (option.yAxis)
        objY = $.extend(true, {}, defaultY, option.yAxis[0]);

    var defaultOpts = {
        // 图表标题
        title: {},
        // 鼠标 hover 提示
        tooltip: {
            trigger: 'axis' //,
                // axisPointer: {
                //     lineStyle: {
                //         color: 'rgba(0,0,0,0)'
                //     }
                // }
        },
        // 图例组件配置
        legend: {
            align: 'left',
            top: 40,
            right: 34,
            itemGap: 35,
            textStyle: {
                color: '#798da8'
            }
        },
        // 坐标系网格配置
        grid: {
            left: 83,
            top: 84,
            right: 34,
            bottom: 61,
            containLabel: false
        },
        // x轴配置
        xAxis: [
            objX
        ],
        // y轴配置
        yAxis: [
            objY
        ]
    }
    var opts = $.extend(true, {}, defaultOpts, option);

    var myChart = echarts.init(dom);
    myChart.setOption(opts);
    return myChart;
}

//渲染柱状图
function renderBarChart(option, dom) {
    // 指定图标的默认配置项
    var objX = null;
    var objY = null;

    var defaultX = {
        type: 'category',
        boundaryGap: true,
        axisLine: {
            show: false
        },
        axisTick: {
            show: false,
            alignWithLabel: true
        },
        axisLabel: {
            margin: 20,
            textStyle: {
                color: '#798da8'
            }
        }
    }

    var defaultY = {
        type: 'value',
        splitNumber: 4,
        axisLine: {
            show: false
        },
        axisTick: {
            show: false,
            alignWithLabel: true
        },
        splitLine: {
            lineStyle: {
                color: '#37526b'
            }
        },
        axisLabel: {
            margin: 21,
            textStyle: {
                color: '#798da8'
            }
        }
    }

    objX = $.extend(true, {}, defaultX, option.xAxis[0]);
    objY = $.extend(true, {}, defaultY, option.yAxis[0]);

    var defaultOpts = {
        // 柱颜色
        color: ['#00e6de'],
        // 鼠标 hover 提示
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        // 图例组件配置
        legend: {
            align: 'left',
            top: 40,
            right: 35,
            itemGap: 16,
            textStyle: {
                color: '#798da8'
            }
        },
        // 坐标系网格配置
        grid: {
            left: 83,
            top: 84,
            right: 34,
            bottom: 61,
            containLabel: false
        },
        // x轴配置
        xAxis: [
            objX
        ],
        // y轴配置
        yAxis: [
            objY
        ]
    }
    var opts = $.extend(true, {}, defaultOpts, option);

    var myChart = echarts.init(dom);

    myChart.setOption(opts);

    return myChart;
}

//更新图表数据
function onlyRefreshChartData(chart, data) {
    if (!chart) return;

    //更新数据
    var option = chart.getOption();
    if (typeof data == 'object') {
        option.series[0].data = data;
    } else {
        $.each(data, function(index, item) {
            option.series[index].data = item;
        });
    }
    chart.setOption(option);
}

//更新x轴
function refreshChartxAxis(chart, data) {
    if (!chart) return;

    //更新数据
    var option = chart.getOption();
    option.xAxis[0].data = data;
    chart.setOption(option);
}

//从图表最后一位插入一条数据
function refreshChart(chart, datas) {
    if (!chart) return;

    //更新数据
    var option = chart.getOption();

    //如果请求的数据x轴没变就只单单更新图表数据，如果x轴有变化将最后一条数据推到图表中并将图表的第一条移除
    if (_.eq(datas[0], option.xAxis[0].data)) {
        $.each(datas, function(index, item) {
            if (index > 0) {
                option.series[index - 1].data = item;
            }
        });
    } else {
        $.each(datas, function(index, item) {
            if (index == 0) {
                option.xAxis[index].data.push(item[item.length - 1]);
                option.xAxis[index].data.shift();
            } else {
                option.series[index - 1].data.push(item[item.length - 1]);
                option.series[index - 1].data.shift();
            }

        });
    }

    chart.setOption(option);
}