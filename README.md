# crazy-xurma
Spring MVC 3 + MyBatis 3 + ExtJS 3.4 CRUD Grid Example

This application is based on Spring framework with the use of SpringMVC.
Domain objects are serialized and deserialized using Jackson JSON. See ExtData and ExtResponse classes.
DB connection is managed by MyBatis engine. 
(Additional links:
http://www.mybatis.org/mybatis-3
http://www.mybatis.org/spring)

UserController is a Spring MVC controller which is wired in to a Ext.data.JsonStore 
with writers configured to send updates to the store as JSON data.

A database field 'uid' is auto-incremented, sequence-based.

You can use sql-scripts under 'resources/scripts' folder to initialize data into Postgresql DB.
