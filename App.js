import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import AppAuthAndroid from './AppAuthAndroid';
import QRCode from 'react-native-qrcode';
import { StackNavigator } from 'react-navigation';
import MainUI from './MainUI';

const App = StackNavigator({
  MainUI: { screen: MainUI }
});

