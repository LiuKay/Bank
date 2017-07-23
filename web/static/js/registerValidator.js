
$(document).ready(function() {
	$('#loginForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            username: {
                message: '姓名无效',
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }
        }
	});
	
    $('#signUpForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            // invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                message: '姓名无效',
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '姓名长度为6-18位'
                    }
                }
            },            
            sign_email: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    }
                }
            },
            optradio: {
                validators: {
                    notEmpty: {
                        message: '性别不能为空'
                    }
                }
            },
            phone: {
                validators: {
                	notEmpty: {
                        message: '手机号不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号长度为11位'
                    },
                    regexp: {
                         regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                         message: '请输入正确格式的手机号码如13*/15*/18*开头'
                     }
                }
            },
            pwd: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '姓名长度为6-18位'
                    },
                    //与重复密码匹配
                    identical: {
                        field: 'confirm_pwd',
                        message: '2次密码不一致'
                    },
                    regexp: {
                         regexp: /^[a-zA-Z0-9_\.]+$/,
                         message: '密码不能含有特殊符号'
                     }
                }
            },
            confirm_pwd: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '姓名长度为6-18位'
                    },
                    identical: {
                        field: 'pwd',
                        message: '2次密码不一致'
                    },
                     regexp: {
                         regexp: /^[a-zA-Z0-9_\.]+$/,
                         message: '密码不能含有特殊符号'
                     }
                }
            }	            
        }
    });
});
