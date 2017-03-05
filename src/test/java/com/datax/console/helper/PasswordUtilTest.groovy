package com.datax.console.helper

import com.fxiaoke.common.PasswordUtil
import spock.lang.Specification

/**
 * PasswordUtilTest
 * Created by wangzk on 17/3/3.
 */
class PasswordUtilTest extends Specification {

  def "Encode & Decode"() {
    given:
    def ds = "hello"

    when:
    def s = PasswordUtil.encode(ds)
    println(s)

    def d = PasswordUtil.decode(s)
    println(d)

    then:
    println("over")
  }

}
