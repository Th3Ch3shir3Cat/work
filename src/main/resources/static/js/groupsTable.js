let $table = $('#GroupsTable')

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

})