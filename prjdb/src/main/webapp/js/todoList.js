/**
 * todoList.js
 */

$(document).ready(function () {
    addNum();

    // 페이지 로드 : list를 전부 찾아서 삭제버튼 넣어주기.
    $('li').append($('<span />').text('X').attr('class', 'close'));
    //$('li').prepend($('<span />').text('').attr('class', 'no'));
    
    function addNum() {
        let lis = $('#myUL>li');
        $('.no').remove();

        $('#myUL>li').each(function(index, item) {
            let spans = $('<span />').text((index+1) + '. ').attr('class', 'no');
            $(item).prepend(spans);
            //item.prepend((index+1)+'. ');
        });
    }
    
    // 삭제버튼 클릭 : list 숨기기
    $('#myUL').on('click','.close', function(e){
        $(this).parent().remove();
        addNum();
    })

    // list 클릭시 : 클릭 된 요소에 토글기능(checked 속성 추가 or 삭제)
    $('#myUL').on('click', 'li', function(e){
        $(this).toggleClass('checked');
    })

    // add 버튼 클릭 : 
    // 1. 입력한 값이 없거나 공백이면 : 값을 입력하라고 메시지 띄우기
    // 2. 입력한 값이 있으면 : list 추가(삭제버튼도 같이)
    // 3. list 추가 후 input에 입력된 값 없애주기
    $('.addBtn').on('click', function(e){
        
        let text = $('#myInput').val();
        let date = ' (Due: ' + $('#myInputDate').val() + ')';
        if(text.trim() == '') {
            alert('입력된 값이 없습니다!');
        } else {
            let li = $('<li />').text(text + date);
            let span = $('<span />').text('X').attr('class', 'close');
            li.append(span);
            $('#myUL').append(li);
            $('#myInput').val('');
            $('#myInputDate').val('');

            addNum();
        }
    })



})

