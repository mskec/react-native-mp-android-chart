import {
  PropTypes,
  requireNativeComponent,
  View
} from 'react-native';

import ChartBase from './ChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'CandleStickChart',
  propTypes: {
    ...ChartBase.propTypes,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        yValues: PropTypes.arrayOf(
          PropTypes.shape({
            shadowH: PropTypes.number.isRequired,
            shadowL: PropTypes.number.isRequired,
            open: PropTypes.number.isRequired,
            close: PropTypes.number.isRequired,
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
          decreasingColor: PropTypes.string,
          decreasingPaintStyle: PropTypes.string,
          increasingColor: PropTypes.string,
          increasingPaintStyle: PropTypes.string
        })
      })),
      xValues: PropTypes.arrayOf(PropTypes.string)
    })
  }
};

export default requireNativeComponent('MPAndroidCandleStickChart', iface);
