import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'CandleStickChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    data: PropTypes.shape({
      dataSets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(
          PropTypes.shape({
            x: PropTypes.number,
            shadowH: PropTypes.number.isRequired,
            shadowL: PropTypes.number.isRequired,
            open: PropTypes.number.isRequired,
            close: PropTypes.number.isRequired,
            marker:PropTypes.string,
          })
        ),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,
          ...ChartDataSetConfig.lineScatterCandleRadar,

          barSpace: PropTypes.number,
          shadowWidth: PropTypes.number,
          shadowColor: PropTypes.string,
          shadowColorSameAsCandle: PropTypes.bool,
          neutralColor: PropTypes.string,
          decreasingColor: PropTypes.string,
          decreasingPaintStyle: PropTypes.string,
          increasingColor: PropTypes.string,
          increasingPaintStyle: PropTypes.string
        })
      })),
    })
  }
};

export default requireNativeComponent('MPAndroidCandleStickChart', iface);
