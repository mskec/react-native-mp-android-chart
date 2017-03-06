import {PropTypes} from 'react';
import {
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
      dataSets: PropTypes.arrayOf(PropTypes.shape({
        values: PropTypes.arrayOf(
          PropTypes.oneOfType([
            PropTypes.shape({
              x: PropTypes.number,
              y: PropTypes.number.isRequired,
              marker:PropTypes.string
            }),
            PropTypes.number
          ])
        ),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,
          ...ChartDataSetConfig.barLineScatterCandleBubble,
          ...ChartDataSetConfig.lineScatterCandleRadar,
          ...ChartDataSetConfig.lineRadar,

          circleRadius: PropTypes.number,
          drawCircles: PropTypes.bool,
          mode: PropTypes.string,
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
      }))

    })
  }
};

export default requireNativeComponent('MPAndroidLineChart', iface);
