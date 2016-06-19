import {
  PropTypes,
  requireNativeComponent,
  View
} from 'react-native';

import BarLineChartBase from './BarLineChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'LineChart',
  propTypes: {
    ...BarLineChartBase.propTypes,

    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        yValues: PropTypes.arrayOf(PropTypes.number),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,
          ...ChartDataSetConfig.lineScatterCandleRadar,
          ...ChartDataSetConfig.lineRadar,

          circleRadius: PropTypes.number,
          drawCircles: PropTypes.bool,
          drawCubic: PropTypes.bool,
          drawCubicIntensity: PropTypes.number,
          circleColor: PropTypes.string,
          circleColors: PropTypes.arrayOf(PropTypes.string),
          circleColorHole: PropTypes.string,
          drawCircleHole: PropTypes.bool,

          dashedLine: PropTypes.shape({
            lineLength: PropTypes.number.isRequired,
            spaceLength: PropTypes.number.isRequired,
            phase: PropTypes.number,
          })
        })
      })),
      xValues: PropTypes.arrayOf(PropTypes.string)
    })
  }
};

export default requireNativeComponent('MPAndroidLineChart', iface);
