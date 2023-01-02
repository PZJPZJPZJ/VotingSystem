var detail_div = 1;
function add1(){
    var input1 = document.createElement('input');
    input1.setAttribute('type', 'text');
    input1.setAttribute('name', 'content');
    input1.setAttribute('class', 'form-control add-content');
    input1.setAttribute('placeholder','选项'+detail_div+'内容')
    input1.setAttribute('id', 'content'+detail_div);
    var btn1 = document.getElementById("org");
    btn1.insertBefore(input1,null);
    var input2 = document.createElement('input');
    input2.setAttribute('type', 'text');
    input2.setAttribute('name', 'picture');
    input2.setAttribute('class', 'form-control add-picture');
    input2.setAttribute('placeholder','图片'+detail_div+'URL')
    input2.setAttribute('id', 'content'+detail_div);
    var btn2 = document.getElementById("org");
    btn2.insertBefore(input2,null);
    detail_div++;
}