import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';

export default class ScanScreen extends React.Component {
  static navigationOptions = { title: 'Scan' };
  
  render() {
    return (
      <View style={styles.container}>
        <Text style={{margin:5}}>Scan</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
