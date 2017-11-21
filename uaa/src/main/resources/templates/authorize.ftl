<html>
<head>
    <style type="text/css">


        svg {
            width: 200px;
            height: 200px;
            margin-left: 45%;
            margin-top: 150px;
        }
        path {
            stroke: #f69f37;
            stroke-linecap: round;
            stroke-width: .2;
            stroke-dasharray: 0, 0, 550, 100;
            stroke-dashoffset: 0;
            stroke-linejoin: round;
        }
        /*@keyframes logo {*/
        /*0% {*/
        /*stroke-dashoffset: -350;*/
        /*}*/
        /*!*37.5% {*!*/
        /*!*stroke: #ed1c24;*!*/
        /*!*}*!*/
        /*!*67.5% {*!*/
        /*!*stroke: #f69f37;*!*/
        /*!*}*!*/
        /*100% {*/
        /*fill: #f69f37;*/
        /*!*stroke-dashoffset: -350;*!*/
        /*!*stroke: #00aeef;*!*/
        /*}*/
        /*}*/
        @keyframes logo1 {
            /*75% {*/
            /**/

            /*}*/
            100% {
                fill: #f69f37;
                stroke-dashoffset: -340;
                /*stroke: #00aeef;*/
            }
        }
        path#logo {
            stroke-dasharray: 0, 0, 1550, 1000;
            animation: logo1 10s infinite linear;
            /*display: none;*/
        }


        @keyframes word {

            100% {
                stroke-dashoffset: -200;
                fill: #f69f37;
            }
            36% {
                stroke-dashoffset: -200;
                fill: #f69f37;
            }
            0% {
                stroke-dashoffset: 0;
                fill: #fff;
            }
        }
        path#word {
            animation: word 10s infinite linear;
        }
        @keyframes word1 {
            100% {
                stroke-dashoffset: -40;
                fill: #f69f37;
            }
            52% {
                stroke-dashoffset: -40;
                fill: #f69f37;
            }
            36% {
                stroke: #f69f37;
                fill: #fff;
            }
            0% {
                stroke: none;
                fill: #fff;
            }
        }
        path#word1 {
            stroke-dasharray: 0, 0, 30, 15;
            animation: word1 10s infinite linear;
        }
        @keyframes word2 {
            0% {
                stroke: none;
                fill: #fff;
            }
            52% {
                stroke: #f69f37;
                fill: #fff;
            }
            68% {
                stroke-dashoffset: -30;
                fill: #f69f37;
                /*stroke: #00aeef;*/
            }
            100% {
                stroke-dashoffset: -30;
                fill: #f69f37;
                /*stroke: #00aeef;*/
            }
        }
        path#word2 {
            stroke-dasharray: 0, 0, 20, 5;
            animation: word2 10s infinite linear;
        }
        @keyframes word3 {
            0% {
                stroke: none;
                fill: #fff;
            }
            68% {
                stroke: #f69f37;
                fill: #fff;
            }
            84% {
                stroke-dashoffset: -30;
                fill: #f69f37;
            }
            100% {
                stroke-dashoffset: -30;
                fill: #f69f37;
            }
        }
        path#word3 {
            stroke-dasharray: 0, 0, 20, 5;
            animation: word3 10s infinite linear;
        }
        @keyframes word4 {
            0% {
                stroke: none;
                fill: #fff;
            }
            84% {
                stroke: #f69f37;
                fill: #fff;
            }
            100% {
                stroke-dashoffset: -30;
                fill: #f69f37;
            }
        }
        path#word4 {
            stroke-dasharray: 0, 0, 20, 5;
            animation: word4 10s infinite linear ;
        }
    </style>
</head>
<body>
<div class="container" style="display: none">
    <h2>授权访问确认</h2>

    <p>
        您将授权名为: "${authorizationRequest.clientId}"(${authorizationRequest.redirectUri}) 的应用 访问您的数据。
    </p>
    <form id="confirmationForm" name="confirmationForm"
          action="../oauth/authorize" method="post">
        <input name="user_oauth_approval" value="true" type="hidden"/>
        <#--<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        <button class="btn btn-primary" type="submit">同意</button>
    </form>
    <form id="denyForm" name="confirmationForm"
          action="../oauth/authorize" method="post">
        <input name="user_oauth_approval" value="false" type="hidden"/>
        <#--<input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
        <button class="btn btn-primary" type="submit">拒绝</button>
    </form>
</div>

<svg version="1.1" id="图层_1"
	 xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="64px" height="64px"
	 viewBox="0 0 64 64" enable-background="new 0 0 64 64" xml:space="preserve">
<g>
	<path id="word1" fill="#FFFFFF" d="M33.528,57.045c-0.373,0-0.725-0.074-1.054-0.222s-0.615-0.35-0.859-0.606
		c-0.244-0.256-0.436-0.556-0.576-0.902c-0.14-0.345-0.211-0.713-0.211-1.105c0-0.434,0.077-0.841,0.232-1.222
		c0.155-0.381,0.367-0.713,0.637-0.998s0.585-0.509,0.944-0.672c0.359-0.163,0.745-0.246,1.158-0.246
		c0.32,0,0.615,0.035,0.885,0.104c0.27,0.069,0.503,0.166,0.699,0.288c0.196,0.122,0.349,0.268,0.459,0.434
		c0.11,0.167,0.166,0.347,0.166,0.539c0,0.086-0.022,0.167-0.067,0.242c-0.045,0.077-0.102,0.143-0.173,0.2
		c-0.071,0.057-0.152,0.102-0.242,0.136c-0.091,0.034-0.182,0.05-0.275,0.05c-0.103,0-0.195-0.019-0.278-0.056
		c-0.082-0.037-0.152-0.086-0.211-0.144c-0.059-0.059-0.103-0.125-0.134-0.2c-0.031-0.075-0.045-0.151-0.045-0.229
		c0-0.132-0.014-0.256-0.043-0.371c-0.029-0.115-0.078-0.215-0.149-0.298c-0.071-0.083-0.169-0.149-0.293-0.197
		c-0.124-0.048-0.283-0.072-0.475-0.072c-0.26,0-0.493,0.053-0.699,0.157c-0.206,0.105-0.381,0.247-0.523,0.424
		c-0.143,0.178-0.251,0.387-0.326,0.627c-0.075,0.24-0.112,0.495-0.112,0.766c0,0.366,0.048,0.713,0.144,1.04
		c0.096,0.327,0.231,0.613,0.406,0.859s0.384,0.44,0.63,0.585s0.519,0.216,0.822,0.216c0.267,0,0.498-0.042,0.693-0.125
		c0.195-0.083,0.365-0.196,0.51-0.339s0.268-0.308,0.371-0.496c0.103-0.189,0.195-0.387,0.278-0.598l0.341,0.139
		c-0.096,0.359-0.226,0.681-0.389,0.965c-0.163,0.284-0.357,0.524-0.579,0.721c-0.223,0.195-0.473,0.345-0.752,0.448
		C34.159,56.993,33.856,57.045,33.528,57.045z"/>
	<path id="word2" fill="#FFFFFF" d="M41.446,56.922c0,0-0.411-0.018-0.81-0.298c-0.234-0.164-0.331-0.486-0.331-0.486
		c-0.135,0.103-0.268,0.208-0.397,0.315c-0.129,0.106-0.27,0.204-0.421,0.293c-0.151,0.089-0.318,0.161-0.501,0.216
		c-0.183,0.055-0.396,0.082-0.637,0.082c-0.224,0-0.427-0.036-0.608-0.11c-0.181-0.073-0.336-0.173-0.464-0.302
		c-0.128-0.128-0.226-0.279-0.293-0.453s-0.101-0.363-0.101-0.566c0-0.246,0.049-0.467,0.147-0.664
		c0.098-0.197,0.231-0.374,0.4-0.531c0.169-0.157,0.366-0.295,0.592-0.416s0.465-0.229,0.717-0.326
		c0.252-0.096,0.513-0.181,0.782-0.256c0.269-0.075,0.531-0.144,0.787-0.208v-0.358c0-0.26-0.026-0.479-0.077-0.659
		c-0.052-0.18-0.125-0.325-0.222-0.434c-0.096-0.11-0.213-0.19-0.35-0.237c-0.137-0.048-0.291-0.072-0.462-0.072
		c-0.192,0-0.349,0.025-0.469,0.075c-0.121,0.049-0.216,0.115-0.285,0.197c-0.069,0.082-0.116,0.175-0.141,0.28
		s-0.037,0.211-0.037,0.317c0,0.1-0.008,0.195-0.024,0.288c-0.016,0.092-0.045,0.173-0.088,0.242s-0.101,0.125-0.173,0.168
		s-0.167,0.064-0.28,0.064c-0.089,0-0.17-0.016-0.242-0.05c-0.073-0.034-0.136-0.081-0.19-0.141
		c-0.054-0.06-0.094-0.132-0.123-0.214c-0.029-0.082-0.043-0.171-0.043-0.267c0-0.189,0.063-0.364,0.187-0.528
		c0.124-0.164,0.292-0.305,0.501-0.424c0.21-0.12,0.455-0.214,0.736-0.283s0.576-0.104,0.885-0.104c0.377,0,0.695,0.036,0.955,0.11
		c0.26,0.073,0.469,0.175,0.63,0.307c0.161,0.132,0.275,0.292,0.347,0.48c0.071,0.189,0.106,0.397,0.106,0.624L41.446,56.922
		L41.446,56.922z M40.305,53.583c-0.163,0.054-0.333,0.114-0.51,0.181s-0.349,0.143-0.518,0.227
		c-0.169,0.083-0.33,0.174-0.483,0.272c-0.153,0.098-0.287,0.202-0.402,0.312c-0.115,0.11-0.207,0.228-0.275,0.352
		s-0.101,0.254-0.101,0.389c0,0.139,0.026,0.271,0.077,0.397s0.12,0.237,0.205,0.331c0.086,0.094,0.184,0.169,0.296,0.224
		c0.112,0.055,0.228,0.082,0.35,0.082c0.128,0,0.25-0.016,0.365-0.05s0.228-0.079,0.336-0.136c0.109-0.057,0.217-0.124,0.326-0.2
		s0.219-0.157,0.333-0.242C40.305,55.721,40.305,53.583,40.305,53.583z"/>
	<path  id="word3" fill="#FFFFFF" d="M46.552,51.499c-0.046-0.083-0.11-0.157-0.19-0.218c-0.08-0.063-0.173-0.112-0.28-0.149
		c-0.106-0.037-0.223-0.056-0.347-0.056c-0.153,0-0.303,0.032-0.451,0.096c-0.148,0.064-0.291,0.151-0.43,0.261
		c-0.139,0.11-0.272,0.238-0.4,0.384s-0.247,0.298-0.358,0.459v-0.91v-0.172v-0.002l-1.774,0.002v0.172h0.643v4.767
		c0,0,0,0.281,0.318,0.539c0.337,0.273,0.813,0.251,0.813,0.251v-3.97c0.078-0.128,0.157-0.247,0.235-0.355
		c0.078-0.109,0.156-0.203,0.232-0.283c0.077-0.08,0.153-0.143,0.229-0.187c0.077-0.045,0.152-0.067,0.227-0.067
		c0.096,0,0.176,0.013,0.24,0.04c0.064,0.026,0.127,0.057,0.19,0.091c0.063,0.034,0.132,0.064,0.208,0.091
		c0.077,0.026,0.179,0.04,0.307,0.04c0.206,0,0.367-0.047,0.483-0.141c0.115-0.094,0.173-0.234,0.173-0.419
		C46.621,51.671,46.598,51.583,46.552,51.499z"/>
	<path id="word4" fill="#FFFFFF" d="M49.786,57.045c-0.387,0-0.751-0.073-1.091-0.218c-0.34-0.146-0.636-0.344-0.891-0.595
		c-0.254-0.251-0.455-0.544-0.603-0.881c-0.148-0.336-0.222-0.697-0.222-1.08c0-0.459,0.068-0.884,0.203-1.275
		c0.135-0.392,0.326-0.729,0.571-1.014c0.246-0.284,0.542-0.507,0.889-0.667c0.347-0.16,0.732-0.24,1.155-0.24
		c0.377,0,0.722,0.066,1.035,0.197c0.312,0.131,0.581,0.308,0.805,0.531s0.398,0.479,0.523,0.771
		c0.124,0.292,0.187,0.598,0.187,0.918h-4.274c0,0.384,0.049,0.74,0.149,1.07c0.1,0.329,0.238,0.613,0.416,0.851
		s0.392,0.425,0.641,0.561c0.249,0.135,0.521,0.203,0.816,0.203c0.249,0,0.487-0.04,0.715-0.12c0.228-0.08,0.434-0.185,0.619-0.315
		c0.185-0.129,0.342-0.279,0.472-0.448c0.129-0.169,0.222-0.342,0.275-0.52l0.326,0.144c-0.082,0.295-0.214,0.572-0.397,0.829
		s-0.397,0.483-0.641,0.675c-0.244,0.192-0.51,0.344-0.801,0.456C50.373,56.989,50.081,57.045,49.786,57.045z M50.922,53.033
		c0-0.217-0.038-0.419-0.115-0.606s-0.181-0.349-0.315-0.486c-0.134-0.137-0.29-0.245-0.469-0.323
		c-0.18-0.078-0.373-0.117-0.579-0.117c-0.171,0-0.336,0.039-0.494,0.117c-0.158,0.078-0.298,0.185-0.421,0.32
		c-0.123,0.135-0.224,0.296-0.304,0.483s-0.129,0.388-0.147,0.606L50.922,53.033z"/>

		<path id="word" fill="#FFFFFF" d="M29.253,49.705c-0.293-0.642-0.73-1.209-1.31-1.701c-0.507-0.433-1.14-0.748-1.9-0.945
	s-1.669-0.295-2.726-0.295h-12.36v0.974h3.726v8.178c0,0,0.068,0.445,0.542,0.783c0.523,0.373,1.361,0.344,1.361,0.344v-9.293
	l6.817,0.002c0.61,0,1.348,0.066,1.7,0.174c0.533,0.164,1.011,0.431,1.383,0.812c0.372,0.381,0.66,0.836,0.867,1.365
	c0.207,0.528,0.31,1.108,0.31,1.738c0,0.723-0.11,1.361-0.328,1.915c-0.219,0.553-0.421,0.829-0.802,1.206
	c-0.381,0.376-0.926,0.595-1.438,0.787c-0.512,0.192-1.238,0.271-1.638,0.288h-1.711v-6.312c0,0-0.263-0.491-0.791-0.704
	C20.478,48.831,19.9,48.91,19.9,48.91v8.135h3.837c0.955,0,1.801-0.137,2.543-0.41c0.74-0.273,1.363-0.647,1.867-1.122
	c0.505-0.475,0.888-1.03,1.151-1.664c0.263-0.635,0.395-1.313,0.395-2.036C29.692,51.049,29.546,50.348,29.253,49.705z"/>
</g>

<path id="logo" fill="#FFFFFF" d="M32.433,42.858c-3.51,0-6.486-1.046-8.606-3.024c-1.876-1.751-2.979-4.134-3.103-6.709
	c-0.249-5.134,2.19-8.766,3.803-11.17c0.209-0.313,0.41-0.611,0.593-0.895c3.05-4.741,6.337-13.142,6.37-13.226
	c0.219-0.561,0.771-0.908,1.377-0.878c0.601,0.039,1.109,0.461,1.255,1.045c0.015,0.06,1.543,6.092,3.897,9.432
	c0.44,0.624,0.29,1.488-0.335,1.928c-0.624,0.44-1.487,0.291-1.928-0.335c-1.351-1.918-2.429-4.499-3.171-6.605
	c-1.283,2.97-3.226,7.164-5.138,10.136c-0.192,0.298-0.402,0.611-0.622,0.94c-1.578,2.35-3.542,5.275-3.337,9.493
	c0.237,4.905,4.664,7.1,8.945,7.1c4.172,0,7.117-2.436,7.502-6.206c0.222-2.172-0.28-4.007-1.453-5.305
	c-1.17-1.295-2.951-1.979-5.149-1.979c-1.556,0-2.778,0.51-3.632,1.514c-0.927,1.092-1.345,2.741-1.09,4.305
	c0.261,1.604,1.915,2.538,3.33,2.483c1.538-0.061,2.172-0.965,2.415-1.499c0.36-0.795,0.32-1.743-0.095-2.256
	c-0.096-0.119-0.39-0.482-1.255-0.291c-0.744,0.163-1.484-0.31-1.647-1.056s0.31-1.484,1.056-1.647
	c1.613-0.351,3.069,0.106,3.998,1.253c1.071,1.323,1.257,3.388,0.463,5.139c-0.859,1.896-2.618,3.034-4.826,3.122
	c-2.987,0.118-5.711-1.99-6.171-4.802c-0.388-2.379,0.252-4.825,1.713-6.543c0.965-1.135,2.745-2.49,5.741-2.49
	c3.003,0,5.494,1,7.203,2.893c1.688,1.868,2.452,4.511,2.153,7.441C42.245,38.493,38.903,42.858,32.433,42.858z"/>
</svg>
<script>
    setTimeout(function () {
        var path = document.querySelector('path#logo');
        // 定义动作
        path.style.animation = 'logo1 10s infinite linear';
        path.style.strokeDasharray= '0, 0, 550, 100';
//		path.style.fill= '#f69f37';
//		path.style.display= 'block';/
    },5500)
</script>

<script type="text/javascript">
   //自动确认授权访问
    function autoAuthorize(){
        document.getElementById('confirmationForm').submit();
    }
    window.load=autoAuthorize();
</script>
</body>
</html>
