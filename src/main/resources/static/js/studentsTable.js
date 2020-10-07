let $table = $('#StudentsTable')
let $newStudent = $('#newStudent')

let today = new Date();
let dd = String(today.getDate()).padStart(2,'0');
let mm = String(today.getMonth() + 1).padStart(2, '0');
let yyyy = today.getFullYear();

today = dd + '.' + mm + '.' + yyyy;


$(document).ready(function () {

    $(function () {
        const group_id = $('#group').val();
        if (group_id !== undefined && group_id !== '' && group_id !== '0')
            $.ajax({
                type: "GET",
                headers: {
                    'Accept': 'application/json'
                },
                url: "/group/" + group_id + "/students",
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
            console.log(ids);
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

    $newStudent.submit(function (e) {
            // Отменяем перезагрузку страницы при сабмите
            e.preventDefault()
            let date_access = today;
            let fio_student = $newStudent.find('#fio');
            let group = $newStudent.find('#group').val();
                $.ajax({
                    type: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify({
                        date_accept: date_access,
                        fio: fio_student.val(),
                        group: {
                            Id: group
                        }
                    }),
                    url: "/api/student/create",
                    success: function (student) {
                        $table.bootstrapTable('append', [{
                            id: student.id,
                            date_accept: student.date_accept,
                            fio: student.fio
                        }]);
                        $("#fio").val('');
                    }
                    ,
                    error: function (data) {
                        console.log(data);
                    }
                });
        }
    )

})