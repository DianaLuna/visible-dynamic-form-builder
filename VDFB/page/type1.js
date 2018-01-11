$(function () {
    $("[data-toggle='popover']").popover();
});
$(function () {
    fb = new Formbuilder({
        selector: '.fb-main',
        bootstrapData: [
            {
                "label": "姓名",
                "field_type": "text",
                "required": true,
                "field_options": {},
                "cid": "c6"
            },
            {
                "label": "手机号码",
                "field_type": "phone",
                "required": true,
                "field_options": {

                },
                "cid": "c10"
            },
            {
                "label": "邮箱",
                "field_type": "email",
                "required": true,
                "field_options": {},
                "cid": "c14"
            },

            {
                "label": "个人简介",
                "field_type": "paragraph",
                "required": false,
                "field_options": {

                },
                "cid": "c14"
            }
        ]
    });
    var formData;
    fb.on('save', function (payload) {
        formData = (JSON.parse(payload))['fields'];
    });
    
    document.getElementById('store').onclick = function () {
        // document.getElementById('messages').innerHTML = formData;
        // var s={"fields":[{"label":"姓名","field_type":"text","required":true,"field_options":{},"cid":"c6"},{"label":"手机号码","field_type":"phone","required":true,"field_options":{},"cid":"c10"},{"label":"邮箱","field_type":"email","required":true,"field_options":{},"cid":"c14"},{"label":"个人简介","field_type":"paragraph","required":false,"field_options":{},"cid":"c14"},{"label":"标题","field_type":"checkboxes","required":true,"field_options":{"options":[{"label":"","checked":false},{"label":"","checked":false}]},"cid":"c15"}]}
        console.log(formData);
        new Formbuilder({
            selector: '#messages',
            bootstrapData: formData
        });
        document.querySelector('#messages>.fb-left').innerHTML=JSON.stringify(formData);
    }
});