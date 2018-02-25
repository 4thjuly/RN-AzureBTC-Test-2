import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import { StackNavigator } from 'react-navigation';
import MainScreen from './MainScreen';
import ScanScreen from './ScanScreen';
import QRScreen from './QRScreen';
import ScanResultScreen from './ScanResultScreen';
import ResizeImageScreen from './ResizeImageScreen';

export default SimpleApp = StackNavigator({
  Main: { screen: MainScreen },
  Scan: { screen: ScanScreen },
  QR: { screen: QRScreen }, 
  ScanResult: { screen: ScanResultScreen },
  ResizeImage: { screen: ResizeImageScreen }
});
