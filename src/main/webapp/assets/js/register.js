jQuery(document).ready(function () {

  /*
   Fullscreen background
   */
  $.backstretch("assets/img/backgrounds/1.jpg");

  $('#top-navbar-1').on('shown.bs.collapse', function () {
    $.backstretch("resize");
  });
  $('#top-navbar-1').on('hidden.bs.collapse', function () {
    $.backstretch("resize");
  });

  /*
   Form
   */
  $('.registration-form fieldset:first-child').fadeIn('slow');

  $('.registration-form input[type="text"], .registration-form input[type="password"], .registration-form textarea').on(
    'focus', function () {
      $(this).removeClass('input-error');
    });

  // next step
  $('.registration-form .btn-next').on('click', function () {
    var parent_fieldset = $(this).parents('fieldset');
    var next_step = $("#registration-form").validate().element($("input"));
    if (next_step) {
      parent_fieldset.fadeOut(400, function () {
        $(this).next().fadeIn();
      });
    }
  });

  // previous step
  $('.registration-form .btn-previous').on('click', function () {
    $(this).parents('fieldset').fadeOut(400, function () {
      $(this).prev().fadeIn();
    });
  });

  // submit
  $('.registration-form').on('submit', function (e) {
    var next_step = $("#registration-form").validate().element($("input"));


    $(this).find('input[type="text"], input[type="password"], textarea').each(function () {
      if ($(this).val() == "") {
        e.preventDefault();
        $(this).addClass('input-error');
      }
      else {
        $(this).removeClass('input-error');
      }
    });

  });


  $('#registration-form').validate({
    rules: {
      form_email: {
        required: true,
        email: true
      },
      form_password: {
        required: true,
        minlength: 6
      },
      form_repeat_password: {
        required: true,
        minlength: 6,
        equalTo: "#form_password"
      },
      form_name: {
        required: true
      },
      form_mobile: {
        required: true
      },
      form_company: {
        required: true
      },
      form_website: {
        required: true
      }
    },
    messages: {
      form_email: "请输入有效的电子邮件地址",
      form_password: {
        required: "请输入密码",
        minlength: "密码长度不能小于6个字符"
      },
      form_repeat_password: {
        required: "请输入密码",
        minlength: "密码长度不能小于6个字符",
        equalTo: "两次输入密码不一致"
      },
      form_name: {
        required: "请输入姓名"
      },
      form_mobile: {
        required: "请输入电话"
      },
      form_company: {
        required: "请输入公司名称"
      },
      form_website: {
        required: "请输入公司网址"
      }
    },
    submitHandler: function() {
      $("#form_validate_message").hide();
      $.ajax({
               url: contextPath + "/validateRegister",
               dataType: "json",
               type: 'POST',
               data: {
                 "email": $('#form_email').val(),
                 "password": $('#form_password').val(),
                 "name": $('#form_name').val(),
                 "company": $('#form_company').val(),
                 "website": $('#form_website').val(),
                 "mobile": $('#form_mobile').val(),
                 "repeatPassword": $('#form_repeat_password').val()
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
