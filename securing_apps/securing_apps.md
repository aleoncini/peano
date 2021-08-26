    SECURING APPLICATIONS WITH Keycloak and OAUTH2-PROXY

    this guide is based on Rarm Nagalingam's article "Authorizing multi-language microservices with oauth2-proxy"
    available at
    https://developers.redhat.com/articles/2021/05/20/authorizing-multi-language-microservices-oauth2-proxy#

1. deploy Keycloak (if you don't have one already available)

    I'm currently using a project and I'm gonna deploy KC into this namespace, remember to substitute the variable with the correct project name

    oc new-app --name access \
    --docker-image=quay.io/keycloak/keycloak \
    -e KEYCLOAK_USER='admin' \
    -e KEYCLOAK_PASSWORD='a_pwd_of_your_choice' \
    -e PROXY_ADDRESS_FORWARDING='true' \
    -n ${PROJECT}

    then I will add a route to expose the service

    oc create route edge --service=access -n ${PROJECT}

    a questo punto sara' possibile accedere alla console di KC

2. configure keycloak to manage access to a secure reverse proxy (that we'll install later)

    access the keycloak console using the credentials you provided

    create a new REALM

    create a client

    give a name of your choice (I have used secure-proxy)

    set access-type from public to confidential

    set a valid callback URL. I will deploy an app with name 'gw' (gateway) so you can build the url using the same of keycloak service, just substitute 'access' with 'gw'
    something like 'https://gw-peano.apps.ocp4.rhocplab.com/oauth2/callback'

    Now we need to configure authorizations for the gateway (configure mappers)

    in the client page select TAB 'Mappers' and create a new group

        name: groups
        type: Group Membership
        token name: groups
        full group path: OFF
        all others: ON

    in the left menu go to Groups and add new groups of membership. In my case I need two groups:

        manager
        basic_user

    Finally create a user to test the environment

    from the menu on the left select Users and add a new user, add the user to the groups you have previously created.

3. deploy the gateway

    In the Rarm's article you can find a git repo containing all the yaml files to deploy the application. You can clone the repo, edit the files for your convenience and create the gateway (deploy the oauth2-proxy server) in one single command.
    Here I have exploded the commands so in case you already have deployed the application you can just pick the single commands you need.

     
