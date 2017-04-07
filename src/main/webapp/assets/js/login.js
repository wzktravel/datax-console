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
      form_email: "请输入一个正确的邮箱",
      form_password: {
        required: "请输入密码",
        minlength: "密码长度不能小于 6 个字母"
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
                 console.log(result);
                 if (result.status == "1") {
                   window.location.href = contextPath + "/overview";
                 } else {
                   $("#form_validate_message").text(result.message).show();
                 }
               }
             });
    }
  });

});
