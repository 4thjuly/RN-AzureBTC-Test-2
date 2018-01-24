'use strict';

import { NativeModules } from 'react-native';

var appAuth = NativeModules.AppAuthIos;

appAuth.test('This is a test');

module.exports = appAuth;

