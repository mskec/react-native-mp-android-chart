import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import ChartBase from './ChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'BubbleChart',
  propTypes: {
    ...ChartBase.propTypes,

    data: PropTypes.shape({
      dataSets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(PropTypes.shape({
          x: PropTypes.number,
          y: PropTypes.number.isRequired,
          size: PropTypes.number.isRequired,
          marker:PropTypes.string,
        })),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,

        })
      }))

    })
  }
};

export default requireNativeComponent('MPAndroidBubbleChart', iface);
