let $table = $('#StudentsTable')


let today = new Date();
let dd = String(today.getDate()).padStart(2,'0');
let mm = String(today.getMonth() + 1).padStart(2, '0');
let yyyy = today.getFullYear();

today = mm + '/' + dd + '/' + yyyy;


$(document).ready(function () {

    $(function () {
        const id = $('#id').val();
        if (id !== undefined && id !== '' && id !== '0')
            $.ajax({
                type: "GET",
                headers: {
                    'Accept': 'application/json'
                },
                url: "/group/" + id + "/students",
                success: function (data) {
                    console.log(data);
                    $table.bootstrapTable('load', data);
                }
                ,
                error: function (data) {
                    console.log(data);
                }
            });
    })

    $(function () {
        $table.bootstrapTable({
            columns: [{
                field: 'date_accept',
                title: 'Дата принятия',
                align: 'center',
            }, {
                field: 'fio',
                title: 'ФИО студента',
                align: 'left',
            },{
                field: 'operate',
                title: "Действия",
                align: 'center',
                valign: 'middle',
                clickToSelect: false,
                events: {
                    'click .remove': function (e, value, row, index) {
                        deleteStudentByIds([row.id])
                    }
                },
                formatter: [
                    '<a class="remove" href="javascript:void(0)" title="Delete">',
                    'Delete',
                    '</a>'
                ].join('')
            }
            ]
        })
    })

    function deleteStudentByIds(ids) {
            $table.bootstrapTable('remove', {
                "field": 'id',
                "values": ids
            });
            // Удаление с сервера

            $.ajax({
                type: "DELETE",
                headers: {
                    'Accept': 'text/html',
                    'Content-Type': 'application/json'
                },
                data : JSON.stringify(ids),
                url: "/api/student/delete/" + ids,
                success: function( data ) {
                    console.log(data)
                }
                ,
                error: function (data) {
                   console.log(data)
                }
            });
    }

    $table.submit(function (e) {
            // Отменяем перезагрузку страницы при сабмите
            e.preventDefault()
            // Обрабатываем значения для клиента с формы
            let date_access = today;
            let fio = $table.find('#fio');
            let idValue = $table.find('#id').val();
                $.ajax({
                    type: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify({
                        date_accept: date_access.val(),
                        fio: fio.val(),
                        id: idValue
                    }),
                    url: "/group/" + id,
                    success: function (student) {
                        $table.bootstrapTable('append', [{
                            date_accept: student.date_accept,
                            fio: student.fio
                        }]);
                        fio.val('');
                    }
                    ,
                    error: function (data) {
                        console.log(data);
                    }
                });
        }
    )

})