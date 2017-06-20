// Ext.namespace('App');

Ext.onReady(function () {

    var springWriter = Ext.extend(Ext.data.JsonWriter, {
        encode: false,
        writeAllFields: true,
        listful: true,
        constructor: function (config) {
            springWriter.superclass.constructor.call(this, config);
        },
        render: function (params, baseParams, data) {
            // override the render function to insert the raw JSON payload
            params.jsonData = data;
        }
    });

    var myWriter = new springWriter();

    var myProxy = new Ext.data.HttpProxy({
        /*        restful: true,
         url: '/users'*/
        api: {
            create: {url: '/users/create', method: 'POST'},
            read: {url: '/users/view', method: 'GET'},
            update: {url: '/users/update', method: 'PUT'},
            destroy: {url: '/users/destroy', method: 'DELETE'}
        }
    });

    var User = Ext.data.Record.create([
        {name: 'uid', type: 'int'},
        {name: 'username', type: 'string'},
        {name: 'password', type: 'string'},
        {name: 'email', type: 'string'}
    ]);

    var myReader = new Ext.data.JsonReader({
        totalProperty: 'total',
        successProperty: 'success',
        idProperty: 'uid',
        root: 'data',
        messageProperty: 'message'
    }, User);

    var store = new Ext.data.Store({
        proxy: myProxy,
        reader: myReader,
        writer: myWriter,
        autoSave: false
    });
    store.load();

    Ext.data.DataProxy.addListener('exception', function (proxy, type, action, options, res) {
        Ext.Msg.show({
            title: 'ERROR',
            msg: res.message,
            icon: Ext.MessageBox.ERROR,
            buttons: Ext.Msg.OK
        });
    });

    var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
    });

    // create grid
    var grid = new Ext.grid.GridPanel({
        store: store,
        columns: [
            {
                header: "ID",
                width: 130,
                sortable: true,
                dataIndex: 'uid',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                }
            },
            {
                header: "NAME",
                width: 170,
                sortable: true,
                dataIndex: 'username',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                }
            },
            {
                header: "PASSWORD",
                width: 160,
                sortable: true,
                dataIndex: 'password',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                }
            },
            {
                header: "EMAIL",
                width: 170,
                sortable: true,
                dataIndex: 'email',
                editor: {
                    xtype: 'textfield',
                    allowBlank: false
                }
            }
        ],
        viewConfig: {forcefit: true},
        plugins: [editor],
        title: 'My Users',
        height: 300,
        width: 600,
        frame: true,
        tbar: [{
            iconCls: 'icon-user-add',
            text: 'Add User',
            handler: function () {
                var e = new User({
                    name: 'New Drug',
                    password: '123123',
                    email: 'new@gmail.com'
                });
                editor.stopEditing();
                store.insert(0, e);
                grid.getView().refresh();
                grid.getSelectionModel().selectRow(0);
                editor.startEditing(0);
            }
        }, {
            iconCls: 'icon-user-delete',
            text: 'Remove User',
            handler: function () {
                editor.stopEditing();
                var s = grid.getSelectionModel().getSelections();
                for (var i = 0, r; r = s[i]; i++) {
                    store.remove(r);
                }
            }
        }, {
            iconCls: 'icon-user-save',
            text: 'Save All Modifications',
            handler: function () {
                store.save();
            }
        }]
    });

    grid.render('users-grid');
});
