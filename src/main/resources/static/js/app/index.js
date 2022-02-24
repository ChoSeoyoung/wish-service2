var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-update').on('click',function(){
            _this.update();
        });
        $('#btn-delete').on('click',function(){
            _this.delete();
        });
        $('#btn-delete-checked').on('click',function(){
            _this.deleteChecked();
        });
    },
    save : function () {
        var data = {
            title : $('#title').val(),
            period : $('#period').val(),
            cost : $('#cost').val(),
            regionType : $("input[name='regionType']:checked").val(),
            travelType : $('#travelType').val()
        };

        $.ajax({
            type:'POST',
            url:'/api/v1/wishes',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href='../wishes';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    update : function(){
        var data = {
            title: $('#title').val(),
            period: $('#period').val(),
            cost: $('#cost').val(),
            regionType : $("input[name='regionType']:checked").val(),
            travelType : $('#travelType').val()
        };

        var id = $('#id').val();

        $.ajax({
            type:'PUT',
            url:'/api/v1/wishes/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href='../../wishes';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    delete : function(){
        var id = $('#id').val();

        $.ajax({
            type:'DELETE',
            url:'/api/v1/wishes/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8',
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href='../../wishes';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    deleteChecked : function() {
        var cnt = $("input[name='rowCheck']:checked").length;
        var checkBoxArr= [];
        $("input[name='rowCheck']:checked").each(function(){
            checkBoxArr.push($(this).val());
        });
        var jData = JSON.parse("{}");
        jData["checkBoxArr"] = checkBoxArr;

        if(cnt==0){
            alert("선택된 글이 없습니다.");
        }else{
            $.ajax({
                type:'POST',
                url:'/api/v1/wishes/delete',
                dataType:'json',
                contentType:'application/json; charset=utf-8',
                data: JSON.stringify(jData)
            }).done(function(){
                alert('글이 삭제되었습니다.');
                window.location.reload();
            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        }
    }
}

main.init();