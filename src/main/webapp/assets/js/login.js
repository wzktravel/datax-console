jQuery(document).ready(function () {
  /*
   Fullscreen background
   */
  $.backstretch("assets/img/backgrounds/1.jpg");

  $('#login-form').validate({
    rules: {
      form_email: {
        required: true,
          email: true
      },
      form_password: {
        required: true,
        minlength: 6
      }
    },
    messages: {
      form_email: "请输入有效的电子邮件地址",
      form_password: {
        required: "请输入密码",
        minlength: "密码长度不能小于6个字符"
      }
    },
    submitHandler: function() {
      $("#form_validate_message").hide();
      $.ajax({
               url: contextPath + "/validateLogin",
               dataType: "json",
               type: 'POST',
               data: {
                 "email": $('#form_email').val(),
                 "password": $('#form_password').val()
               },
               success: function (result) {
                 if (result.status === "1") {
                   if (url === "") {
                     window.location.href = contextPath + "/overview";
                   } else {
                     window.location.href = contextPath + url;
                   }
                 } else {
                   $("#form_validate_message").text(result.message).show();
                 }
               }
             });
    }
  });

});
