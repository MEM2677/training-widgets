INSERT INTO guifragment (code, widgettypecode, plugincode, gui, defaultgui, locked) VALUES ('random_number_ognl', 'random-number-ognl', NULL, NULL, '<#assign s=JspTaglibs["/struts-tags"]>

<h3>Hello Struts2 world!</h3>
<div>This widget is rendered using a FreeMarker script<div> </br>

<div>
Here is a random number:&nbsp;<@s.property value="number.output" />
</br>UI language code:&nbsp;<@s.property value="currentLang.code" />
</br>current user:&nbsp;<@s.property value="uiUser.username" />
</div>', 1);

INSERT INTO guifragment (code, widgettypecode, plugincode, gui, defaultgui, locked) VALUES ('random_number_ognl_error', 'random-number-ognl', NULL, NULL, '<pre>
   __   _ __   _ __   ___   _ __
 /''__`\/\`''__\/\`''__\/ __`\/\`''__\
/\  __/\ \ \/ \ \ \//\ \L\ \ \ \/
\ \____\\ \_\  \ \_\\ \____/\ \_\
 \/____/ \/_/   \/_/ \/___/  \/_/

</pre>', 1);