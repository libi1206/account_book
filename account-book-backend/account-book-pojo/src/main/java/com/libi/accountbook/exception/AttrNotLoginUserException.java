package com.libi.accountbook.exception;

import com.libi.accountbook.exception.base.BaseException;

/**
 * @author libi
 * 若修改的字段不是当前用户的，就会抛出这个异常
 */
public class AttrNotLoginUserException extends BaseException {
}
