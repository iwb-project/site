
bees app:create --appid dev --type tomcat
bees app:deploy --appid dev site*.war --type tomcat -P iwb.context=prod --message "deployment message"
bees config:set --appid dev -R privateApp.secretKey=<secret-key-goes-here>
bees app:restart -appid dev
