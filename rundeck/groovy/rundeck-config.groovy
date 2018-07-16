loglevel.default = "INFO"
rdeck.base = "/var/lib/rundeck"
rss.enabled = false
rundeck.v14.rdbsupport = true
dataSource {
        dbCreate = "update"
        url = "jdbc:mysql://{MYSQL_HOST}/{MYSQL_DATABASE}?autoReconnect=true"
        username = "{MYSQL_USER}"
        password = "{MYSQL_PASSWORD}"
}

grails {
        serverURL = "http://{RUNDECK_HOST_NAME}"
        mail {
                host = "{SMTP_HOST}"
                port = {SMTP_PORT}
                username = "{SMTP_USERNAME}"
                password = "{SMTP_PASSWORD}"
                props = [{SMTP_PROPS}]
        }
}
