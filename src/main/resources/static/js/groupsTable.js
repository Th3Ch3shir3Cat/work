let $table = $('#GroupsTable')
let $addNewGroupFrom = $('#groupForm')

$(document).ready(function () {


    function onEditClick(value, row, index) {
        window.location = '/group/' + row.Id;
    }

    $(function () {
        $table.bootstrapTable('destroy').bootstrapTable({
            columns: [{
                field: 'number',
                title: 'Номер',
                align: 'center',
            }, {
                field: 'numerus_students',
                title: 'Количество студентов',
                align: 'center',
            },{
                field: 'operate',
                title: "Действия",
                align: 'center',
                valign: 'middle',
                clickToSelect: false,
                events: {
                    'click .edit': function (e, value, row, index) {
                        onEditClick(value, row, index)
                    }
                },
                formatter: [
                    '<a class="edit" href="javascript:void(0)" title="Edit">',
                    'Edit',
                    '</a>'
                ].join('')
            }
            ]
        })
    })

    $addNewGroupFrom.submit(function (e){
        e.preventDefault();
        let numberGroup = $addNewGroupFrom.find('#number');
        if(numberGroup.val() === ''){
            numberGroup.addClass('is-invalid');
        }else{
            numberGroup.removeClass('is-invalid');
            $.ajax({
                type: "POST",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({
                    number: numberGroup.val(),
                }),
                url: "/group",
                success: function(data){
                    data = $(data).find('#group');
                    window.location = "/group/"+data.val();
                },
                error: function (){
                  console.log("sdf");
                }
            })
        }
    })

})